package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.DataOptNode;

@Component
public class JavaDataHashBuilder extends JavaFileWriter {
	private String desc;
	private String md5;
	private StringBuilder t2c = new StringBuilder();
	private StringBuilder c2t = new StringBuilder();
	public JavaDataHashBuilder(){
		super();
	}
	
	public void addNode(DataOptNode node){
		if(getDesc() != null){
			fields.append("\t/** ").append(getDesc()).append(" */\r\n");
		}
		
		t2c.append("\t\tType2Class.put(" + node.getDataId() + ", \""+ props.getPackData() + "."+ node.getName() + "\");\n");
		c2t.append("\t\tClass2Type.put(\"" + props.getPackData() + "."+ node.getName() + "\", "+ node.getDataId() + ");\n");
	}

	@Override
	protected void reset(){
		super.reset();
		t2c.delete(0, t2c.length());
	}
	public void frush(){
		String codeName = "DataHash";
		String outPack = props.getPack();
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String packageinfo = "package " + outPack + ";\r\n\r\n";
		addImport("java.util.HashMap");
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
		classInfo.append("\n\tpublic static HashMap<Integer,String>Type2Class;");
		classInfo.append("\n\tpublic static HashMap<String,Integer>Class2Type;");
		classInfo.append("\n\tstatic{\n");
		classInfo.append("\t\tType2Class = new HashMap<>();\n");
		classInfo.append(t2c);
		classInfo.append("\n\t\tClass2Type = new HashMap<>();\n");
		classInfo.append(c2t);
		classInfo.append("\t}\n");
		
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
