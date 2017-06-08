package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.DataOptNode;

@Component
public class AsDataHashBuilder extends AsFileWriter{
	
	private String md5;
	private StringBuilder consts = new StringBuilder();
	public void addClass(DataOptNode node){
		addImport(props.getPackData()+"." + node.getName());
		consts.append("\t\tHASH["+node.getDataId()+"] = "+ node.getName() + ";\n");
	}
	
	public void frush(){
		String fileName = "DataHash";
		String outPack = props.getPack();
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String packageinfo = "package " + outPack +" {\r\n\r\n";
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(getMd5() != null){
			classInfo.append("\t * md5:" + getMd5() + "\r\n" );
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(fileName);
		classInfo
		//		.append(" implements ISocketData")
		.append("{\r\n");
		
		//		addStatics
		classInfo.append("\t\tpublic static const HASH:Array = new Array();\n");
		classInfo.append(consts);
		//		addConstruct
		classInfo.append("\t\tpublic function "+fileName+"(");
		classInfo.append(params);
		classInfo.append("){\r\n")
		.append(constructs)
		.append("\t\t}\r\n");
		
		classInfo.append(fields);
		classInfo.append(methods);
		classInfo.append("\r\n");
		classInfo.append("\t}\r\n");
		classInfo.append("}");
		try {
			File file = new File(dir, fileName + ".as");
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
