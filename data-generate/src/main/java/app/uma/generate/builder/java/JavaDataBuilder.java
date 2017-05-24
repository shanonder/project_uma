package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.icday.builds.CellVO;
import com.icday.utils.CreateFileUtil;

import config.Config;

public class JavaDataBuilder extends JavaDataWriter{
	private static String outDir;
	private static String outPack;
	private static String packageinfo;
	private static String dir;
	
	static {
		outPack = Config.p.getProperty("packname") + ".datas";
		outDir = Config.p.getProperty("outDir");
		String t = outPack.replace(".", "/");
		dir = outDir + t + "/";
		packageinfo = "package " + outPack + ";\r\n\r\n";
		CreateFileUtil.createDir(dir);
	}
	
//	private StringBuilder decodes;
//	private StringBuilder encodes;
	public JavaDataBuilder(String name,String parent,ArrayList<CellVO> cells,String desc,String md5){
		super();
//		decodes = new StringBuilder();
//		encodes = new StringBuilder();
		String codeName = upperFirestChar(name);
		File file = new File(dir, codeName + ".java");
		System.out.println(file.getAbsolutePath());
		addImport("java.io.Serializable");

		for(CellVO cvo : cells){
			optCell(cvo);
		}
		classInfo.append(imports);
		classInfo.append("\t/**\r\n\t * 此类由").append(Config.APP_NAME).append("自动生成\r\n");
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
