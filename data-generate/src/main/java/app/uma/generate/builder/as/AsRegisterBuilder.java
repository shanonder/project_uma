package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.context.ApplicationContext;

import app.uma.generate.SpringContextUtil;
import app.uma.generate.builder.GenerateVersion;
import app.uma.generate.config.GeneralBeans;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;

public class AsRegisterBuilder extends AsCodeFileWriter {
//	private static String outDir;
//	private static String outPack;
//	private static String dir;
	private String packageinfo;
//	static {
//		outPack = Config.p.getProperty("flashpack");
//		outDir = Config.p.getProperty("flashDir");
//		String t = outPack.replace(".", "/");
//		dir = outDir + t + "/";
//		CreateFileUtil.createDir(dir);
//	}
	private String outPack;
	
	private String codeName;
	private StringBuilder constructs;
	private String desc;
	private String md5;
	
	private StringBuilder regists;
	public AsRegisterBuilder(String codeName,String desc , String md5) {
		super();
		map = new HashMap<>();
		this.codeName = codeName;
		this.desc = desc;
		this.md5 = md5;
		regists = new StringBuilder();
		addImport("flash.net.registerClassAlias");
	}
	
	public void init(){
//		outPack = 
	}
	
	public void frush(){
		
		ApplicationContext context = SpringContextUtil.getApplicationContext();
//		GenerateVersion version = context.getBean(GenerateVersion.class);
		Config config = context.getBean(Config.class);
		CodeProperties props = (CodeProperties)context.getBean(GeneralBeans.AS_PROPERTIES);
		if(desc != null){
			classInfo.append("\t * desc:" + desc + "\r\n" );
		}
		packageinfo = "package " + props.getPackurl() + "{\r\n\r\n";
		
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(md5 != null){
			classInfo.append("\t * md5:" + md5 + "\r\n" );
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(codeName);
		classInfo
		.append("{\r\n");
		classInfo.append(fields);
		
		classInfo.append("\t\tpublic static function regist():void{\r\n")
		.append(regists)
		.append("\t\t}\r\n");
		
		classInfo.append(methods);
		classInfo.append("\r\n");
		classInfo.append("\t}\r\n");
		classInfo.append("}");
		try {
			File file = new File(props.getPath(), codeName + ".as");
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
	private HashMap<String, String> map;
	public void addData(String name) {
		if(map.containsKey(name)){
			return;
		}
		addImport(outPack + ".datas." + name);
		regists.append("\t\t\tregisterClassAlias(\"").append(outPack).append(".datas.").append(name).append("\",").append(name).append(");\r\n");
	}
}
