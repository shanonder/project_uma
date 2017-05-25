package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import app.uma.generate.node.CellVO;

public class JavaDataBuilder extends JavaDataWriter{
	

	public JavaDataBuilder(String name,String parent,ArrayList<CellVO> cells,String desc,String md5){
		super();
		String outPack = props.getPack() + ".datas";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String packageinfo = "package " + outPack + ";\r\n\r\n";
		for (String pock :props.getDataImport()){
			addImport(pock);
		}

		String codeName = upperFirestChar(name);
		File file = new File(dir, codeName + ".java");
		System.out.println(file.getAbsolutePath());
		addImport("java.io.Serializable");

		for(CellVO cvo : cells){
			optCell(cvo);
		}
		classInfo.append(imports);
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(md5 != null){
			classInfo.append("\t * md5:" + md5 + "\r\n" );
		}
		classInfo.append("\t */\r\n");
		classInfo.append("public class ").append(codeName);
		if(parent != null){
			classInfo.append(" extends ").append(parent);
		}
		classInfo.append(" implements Serializable{\r\n");
//		classInfo.append("\tpublic byte[] encode(){\r\n");
//		classInfo.append(encodes);
//		classInfo.append("\t}\r\n");
		
//		classInfo.append("\tpublic decode(byte[]){\r\n");
//		classInfo.append(decodes);
//		classInfo.append("}\r\n");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void optCell(CellVO cvo) {
		String type = typeTrans(cvo.type);
		fields.append(getFieldStr(cvo.key, type, cvo.desc));
		methods.append(getMethodStr(cvo.key, type));
//		fields.append(getPublicFieldStr(cvo.key, type, cvo.desc));
	}
	

}
