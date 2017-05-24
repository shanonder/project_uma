package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.icday.utils.CreateFileUtil;

import config.Config;

public class JavaProtocolConstBuilder extends JavaDataWriter {
	private static String outDir;
	private static String outPack;
	private static String packageinfo;
	private static String dir;
	static {
		outPack = Config.p.getProperty("packname") + ".consts";
		outDir = Config.p.getProperty("outDir");
		String t = outPack.replace(".", "/");
		dir = outDir + t + "/";
		packageinfo = "package " + outPack + ";\r\n\r\n";
		CreateFileUtil.createDir(dir);
	}

	private String codeName;
	private String desc;
	private String md5;
	
	public JavaProtocolConstBuilder(String codeName,String desc , String md5){
		super();
		this.codeName = codeName;
		this.desc = desc;
		this.md5 = md5;
	}
	
	public void addCmd(String name,String cmd,String desc){
		if(desc != null){
			fields.append("\t/** ").append(desc).append(" */\r\n");
		}
		fields.append("\tpublic static int " + name + "  = " + cmd).append(";\r\n");

	}

	public void frush(){
		if(desc != null){
			classInfo.append("\t * md5:" + desc + "\r\n" );
		}
		classInfo.append("\t/**\r\n\t * 此类由").append(Config.APP_NAME).append("自动生成\r\n");
		if(md5 != null){
			classInfo.append("\t * md5:" + md5 + "\r\n" );
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(codeName);
		classInfo
//		.append(" implements ISocketData")
		.append("{\r\n");
		classInfo.append(fields);
		classInfo.append(methods);
		classInfo.append("\r\n");
		classInfo.append("}");
		try {
			File file = new File(dir, codeName + ".java");
			System.out.println(file.getAbsolutePath());
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