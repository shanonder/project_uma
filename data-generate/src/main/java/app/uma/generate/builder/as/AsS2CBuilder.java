package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.icday.builds.CellVO;
import com.icday.utils.CreateFileUtil;

import config.Config;

public class AsS2CBuilder extends AsCodeFileWriter{
	private static String outDir;
	private static String outPack;
	private static String packageinfo;
	private static String dir;
	static {
		outPack = Config.p.getProperty("flashpack") + ".responses";
		outDir = Config.p.getProperty("flashDir");
		String t = outPack.replace(".", "/");
		dir = outDir + t + "/";
		packageinfo = "package " + outPack + "{\r\n\r\n";
		CreateFileUtil.createDir(dir);
	}
	public AsS2CBuilder(String codeName,String cmd, ArrayList<CellVO> cells, String md5){
		super();
		File file = new File(dir, codeName + ".as");
		System.out.println(file.getAbsolutePath());
		addImport("flash.utils.ByteArray");

		classInfo.append("\t/**\r\n\t * 此类由").append(Config.APP_NAME).append("自动生成\r\n");
		if(md5 != null){
			classInfo.append("\t * md5:" + md5 + "\r\n" );
		}
		params.append("bytes:ByteArray");
		for(CellVO cvo : cells){
			optCell(cvo);
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(codeName).append("{\r\n");
		classInfo.append(fields);
		classInfo.append("\t\tpublic function "+codeName+"(");
		classInfo.append(params);
		classInfo.append("){\r\n")
		.append(constructs)
		.append("\t\t}\r\n");
		
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
	
	private void optCell(CellVO cvo) {
		writeConstructs(cvo);
		String type = typeTrans(cvo.type);
		fields.append(getPublicFieldStr(cvo.key, type, cvo.desc));
	}
	
	private void writeConstructs(CellVO cvo){
		constructs.append("\t\t\t").append(cvo.key).append(" = ");
		if(cvo.type.equals("List")){
			constructs.append("bytes.readObject();\r\n");
		}
		else if(cvo.type.equals("Int") || cvo.type.equals("int")){
			constructs.append("bytes.readInt();\r\n");
		}
		else if(cvo.type.equals("Double") || cvo.type.equals("double")){
			constructs.append("bytes.readDouble();\r\n");
		}
		else if(cvo.type.equals("Short") ||  cvo.type.equals("short")){
			constructs.append("bytes.readShort();\r\n");
		}
		else if(cvo.type.equals("String") ||  cvo.type.equals("string")){
			constructs.append("bytes.readUTF();\r\n");
		}
		else if(cvo.type.equals("AMF")){
			constructs.append("bytes.readObject();\r\n");
		}else{
			constructs.append("bytes.readObject();\r\n");
		}
	}
}
