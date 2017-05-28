package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.DataOptNode;

@Component
public class JavaDataBuilder extends JavaFileWriter{
	

	public JavaDataBuilder(){
		super();

	}

	private void optCell(CellVO cvo) {
		String type = typeTrans(cvo.type);
		fields.append(getFieldStr(cvo.key, type, cvo.desc));
		methods.append(getMethodStr(cvo.key, type));
//		fields.append(getPublicFieldStr(cvo.key, type, cvo.desc));
	}

	public void frush(DataOptNode node) {
		reset();
		String outPack = props.getPackData();
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String packageinfo = "package " + outPack + ";\r\n\r\n";
		for (String pock :props.getDataImport()){
			addImport(pock);
		}

		String codeName = upperFirestChar(node.getName());
		File file = new File(dir, codeName + ".java");
		addImport("java.io.Serializable");

		for(CellVO cvo : node.cells){
			optCell(cvo);
		}
		classInfo.append(imports);
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.getMd5() != null){
			classInfo.append("\t * md5:" + node.getMd5() + "\r\n" );
		}
		classInfo.append("\t */\r\n");
		classInfo.append("public class ").append(codeName);
		if(node.getParent() != null){
			classInfo.append(" extends ").append(node.getParent());
		}
		classInfo.append(" implements Serializable{\r\n");
		classInfo.append("\tprivate static final long serialVersionUID = 1L;\r\n");
		classInfo.append(fields);
		classInfo.append(methods);
		classInfo.append("\r\n");
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
