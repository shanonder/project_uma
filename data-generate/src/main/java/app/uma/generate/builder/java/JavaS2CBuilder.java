package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import app.uma.generate.vo.CellVO;

public class JavaS2CBuilder extends JavaDataWriter {
//	private static String outDir;
//	private static String outPack;
//	private static String packageinfo;
//	private static String dir;
//	private static String packResponse;
//	static {
//		outPack = Config.p.getProperty("packname") + ".responses";
//		outDir = Config.p.getProperty("outDir");
//		packResponse = Config.p.getProperty("packResponse");
//		String t = outPack.replace(".", "/");
//		dir = outDir + t + "/";
//		
//		
//		packageinfo = "package " + outPack + ";\r\n\r\n";
//		CreateFileUtil.createDir(dir);
//	}
	
	public JavaS2CBuilder(String codeName,String cmd, ArrayList<CellVO> cells, String md5){
		super();
		File file = new File(dir, codeName + ".java");
		System.out.println(file.getAbsolutePath());
		addImport(packResponse);

		params.append("int status ");
		for(CellVO cvo : cells){
			params.append(", ").append(typeTrans(cvo.type)).append(" ").append(cvo.key);
			optCell(cvo);
		}
		classInfo.append(imports);
		classInfo.append("/**\r\n * 此类由").append(Config.APP_NAME).append("自动生成\r\n");
		if(md5 != null){
			classInfo.append(" * md5:" + md5 + "\r\n" );
		}
		classInfo.append(" */\r\n");
		classInfo.append("public class ")
		.append(codeName).append("  extends ServerResponse").append("{\r\n");
		classInfo.append("\tpublic "+codeName+"(");
		classInfo.append(params);
		classInfo.append(") throws Exception{\r\n");
		classInfo.append("\t\tsuper("+cmd + " , status);\r\n");
		classInfo.append(constructs).append("\r\n");
		classInfo.append("\t}\r\n\r\n");
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
	
	private void optCell(CellVO cvo) {
		// TODO Auto-generated method stub
		writeConstructs(cvo);
//		String type = typeTrans(cvo.type);
//		fields.append(getFieldStr(cvo.key, type, cvo.desc));
//		methods.append(getMethodStr(cvo.key, type));
	}

	
	private void writeConstructs(CellVO cvo){
		constructs.append("\t\t");
		if(cvo.type.contains("List")){
			constructs.append("output.writeAMFObject(");
			constructs.append(cvo.key).append(" == null ? ").append("new ArrayList<>() : ").append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equals("Int") || cvo.type.equals("int")){
			constructs.append("output.writeInt(");
			constructs.append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equals("Double") || cvo.type.equals("double")){
			constructs.append("output.writeDouble(");
			constructs.append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equals("Short") ||  cvo.type.equals("short")){
			constructs.append("output.writeShort(");
			constructs.append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equals("String") ||  cvo.type.equals("string")){
			constructs.append("output.writeUTF(");
			constructs.append(cvo.key).append(" == null ? ").append("\"\" : ").append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equals("AMF")){
			constructs.append("output.writeAMFObject(");
			constructs.append(cvo.key).append(" == null ? ").append("new Object() : ").append(cvo.key).append(");\r\n");
		}else{
			constructs.append("output.writeAMFObject(");
			constructs.append(cvo.key).append(" == null ? ").append("new Object() : ").append(cvo.key).append(");\r\n");
		}
		
	}
}
