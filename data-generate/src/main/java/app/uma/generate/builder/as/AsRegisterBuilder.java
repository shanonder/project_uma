package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class AsRegisterBuilder extends AsCodeFileWriter {
	
	
	
	private String codeName;
	private String desc;
	private String md5;
	private StringBuilder regists;
	
	public AsRegisterBuilder() {
		super();
		
		map = new HashMap<>();
		this.codeName = "DataRegister";
		regists = new StringBuilder();
		addImport("flash.net.registerClassAlias");
	}
	

	
	public void frush(){
		String outPack = props.getPack();
		String packageinfo = "package " + outPack +" {\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		for (String pock :props.getResponseImport()){
			addImport(pock);
		}
		if(desc != null){
			classInfo.append("\t * desc:" + desc + "\r\n" );
		}
		
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(getMd5() != null){
			classInfo.append("\t * md5:" + getMd5() + "\r\n" );
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
			File file = new File(dir, codeName + ".as");
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
		addImport(config + ".datas." + name);
		regists.append("\t\t\tregisterClassAlias(\"").append(props.getPack()).append(".datas.").append(name).append("\",").append(name).append(");\r\n");
	}



	public String getMd5() {
		return md5;
	}



	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
