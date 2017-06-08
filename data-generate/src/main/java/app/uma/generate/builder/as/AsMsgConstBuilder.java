package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.MsgOptNode;
import app.uma.utils.StringUtil;

@Component
public class AsMsgConstBuilder extends AsFileWriter {

	//	private StringBuilder constructs;
	//	private String desc;
	private String md5;
	private String codeName;
	public AsMsgConstBuilder(){
		super();
		this.codeName = "ProtocolConst";
	}
	public void addCmd(MsgOptNode node){
		if(node.getDesc() != null){
			fields.append("\t\t/** ").append(node.getDesc()).append(" */\r\n");
		}
		fields.append("\t\tpublic static const " + node.getName() + StringUtil.UpperCaseFirst(node.getType()) + " : int = " + node.getCmd()).append(";\r\n");
	}

	public void frush(){
		String outPack = props.getPack() + ".consts";
		String packageinfo = "package " + outPack +" {\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";

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
}
