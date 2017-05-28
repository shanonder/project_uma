package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.MsgOptNode;
import app.uma.utils.StringUtil;

@Component
public class JavaMsgConstBuilder extends JavaFileWriter {

	private String desc;
	private String md5;
	
	public JavaMsgConstBuilder(){
		super();

	}
	
	public void addCmd(MsgOptNode node){
		if(getDesc() != null){
			fields.append("\t/** ").append(getDesc()).append(" */\r\n");
		}
		fields.append("\tpublic static int " + node.getName() + StringUtil.UpperCaseFirst(node.getType()) + "  = " + node.getCmd()).append(";\r\n");

	}

	public void frush(){
		String codeName = "ProtocolConst";
		String outPack = props.getPack() + ".consts";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String packageinfo = "package " + outPack + ";\r\n\r\n";

		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(getDesc() != null){
			classInfo.append("\t * " + getDesc() + "\r\n" );
		}
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
		classInfo.append("}");
		try {
			File file = new File(dir, codeName + ".java");
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
