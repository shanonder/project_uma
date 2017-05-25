package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.MessageOptNode;

public class AsC2SBuilder extends AsCodeFileWriter {
	public AsC2SBuilder(){
		super();
		
	}
	
	public void frush(MessageOptNode node){
		String outPack = props.getPack() + ".request";
		String packageinfo = "package " + outPack +" {\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String codeName = node.getName() + "Request";
		for (String pock :props.getRequestImport()){
			addImport(pock);
		}
		File file = new File(dir, codeName + ".as");
		System.out.println(file.getAbsolutePath());
		addImport("com.icday.net.socket.SocketRequestBase");
		addImport("com.icday.net.interfaces.INetRequest");

		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.getMd5() != null){
			classInfo.append("\t * md5:" + node.getMd5() + "\r\n" );
		}
		int size = node.c2sCells.size();
		for(int i = 0 ; i < size ; ++i){
			CellVO cvo = node.c2sCells.get(i);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
}
