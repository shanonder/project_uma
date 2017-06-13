package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.DataOptNode;

@Component
public class AsDataHashBuilder extends AsFileWriter{
	
	private String md5;
	private StringBuilder c2ts = new StringBuilder();
	private StringBuilder t2rs = new StringBuilder();
	private StringBuilder c2ws = new StringBuilder();
	
	protected void reset(){
		super.reset();
		c2ts.delete(0, c2ts.length());
		t2rs.delete(0, t2rs.length());
		c2ws.delete(0, c2ws.length());
	}
	
	public void addClass(DataOptNode node){
		addImport(props.getPackData()+"." + node.getName());
		String type = props.getPackData() + "::" + node.getName();
		c2ts.append("\t\tClassname2Type[\""+ type + "\"] = " + node.getDataId() + ";\n");
		c2ws.append("\t\tClassName2Write[\"" + type + "\"] = " + node.getName() + ".write;\n");
		t2rs.append("\t\tType2Read["+node.getDataId()+"] = " + node.getName() + ".read;\n");
	}
	
	public void frush(){
		addImport("flash.utils.Dictionary");
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
		classInfo.append("\t\tpublic static const Classname2Type:Dictionary = new Dictionary();\n");
		classInfo.append("\t\tpublic static const ClassName2Write:Dictionary = new Dictionary();\n");
		classInfo.append("\t\tpublic static const Type2Read:Dictionary = new Dictionary();\n\n");
		classInfo.append(c2ts).append("\n");
		classInfo.append(c2ws).append("\n");
		classInfo.append(t2rs).append("\n");
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
