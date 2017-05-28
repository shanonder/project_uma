package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.MsgOptNode;
@Component
public class AsC2SBuilder extends AsFileWriter {
	public AsC2SBuilder(){
		super();
	}
	
	public void frush(MsgOptNode node){
		reset();
		String outPack = props.getPackRequest();
		String packageinfo = "package " + outPack +" {\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String codeName = node.getName() + "Request";
		for (String pock :props.getRequestImport()){
			addImport(pock);
		}
		File file = new File(dir, codeName + ".as");

		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.getMd5() != null){
			classInfo.append("\t * md5:" + node.getMd5() + "\r\n" );
		}
		int size = node.cells.size();
		for(int i = 0 ; i < size ; ++i){
			CellVO cvo = node.cells.get(i);
			if(i == 0){
				params.append(cvo.key + ":" + typeTrans(cvo.type));
			}else{
				params.append(" , " + cvo.key + ":" + typeTrans(cvo.type));
			}
			addWrite(cvo);
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(codeName).append(" extends SocketRequestBase implements INetRequest{\r\n");
		classInfo.append("\t\tpublic function "+codeName+"(");
		classInfo.append(params);
		classInfo.append("){\r\n")
		.append("\t\t\tsuper("+node.getCmd() + ");\r\n")
		.append(constructs)
		.append("\t\t}");
		classInfo.append(fields);
		classInfo.append(methods);
		classInfo.append("\r\n");
		classInfo.append("\t}\r\n");
		classInfo.append("}");
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(packageinfo);
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
			logger.info(codeName + " init success...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
 
}
