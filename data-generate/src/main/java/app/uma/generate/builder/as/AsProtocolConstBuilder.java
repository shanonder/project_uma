package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import app.uma.generate.node.MessageOptNode;

public class AsProtocolConstBuilder extends AsCodeFileWriter {

//	private StringBuilder constructs;
//	private String desc;
	private String md5;
	private String codeName;
	public AsProtocolConstBuilder(){
		super();
		this.codeName = "ProtocolConst";
	}
	
	public void addCmd(MessageOptNode node){
		if(node.getDesc() != null){
			fields.append("\t\t/** ").append(node.getDesc()).append(" */\r\n");
		}
		fields.append("\t\tpublic static const " + node.getName() + " : int = " + node.getCmd()).append(";\r\n");
	}

	public void frush(){
		String outPack = props.getPack() + ".consts";
		String packageinfo = "package " + outPack +" {\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		
		outPack = props.getPack() + ".";
		dir = props.getPath() + outPack.replace(".", "/") + "/";
		packageinfo = "package " + outPack + "{\r\n\r\n";
		
//		if(getDesc() != null){
//			classInfo.append("\t * md5:" + getDesc() + "\r\n" );
//		}
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(getMd5() != null){
			classInfo.append("\t * md5:" + getMd5() + "\r\n" );
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

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

//	public String getDesc() {
//		return desc;
//	}
//
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}
}
