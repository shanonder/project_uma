package app.uma.generate.builder.java;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import app.uma.generate.config.GeneralBeans;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;

public class JavaDataWriter {
	@Autowired
	protected Config config;
	
	@Autowired
	@Qualifier(GeneralBeans.JAVA_PROPERTIES)
	protected CodeProperties props;
	
	public JavaDataWriter(){
		init();
	}

	private void init() {
		classInfo = new StringBuilder();
		imports = new StringBuilder();
		params = new StringBuilder();
		constructs = new StringBuilder();
		fields = new StringBuilder();
		methods = new StringBuilder();
	}

	protected static final Logger logger = Logger.getLogger(JavaDataWriter.class);

	protected StringBuilder classInfo;
	protected StringBuilder imports;
	protected StringBuilder constructs;
	protected StringBuilder fields;
	protected StringBuilder methods;
	protected StringBuilder params;

	private ArrayList<String> importList;
	protected void addImport(String text) {
		if(importList == null){
			importList = new ArrayList<>();
		}
		if(!importList.contains(text)){
			importList.add(text);
			imports.append("import "+ text + ";\n");
		}
	}

	protected Object getPublicFieldStr(String key, String type, String desc) {
		StringBuilder sb = new StringBuilder();
		if (desc != null) {
			sb.append("\t/**\r\n\t * ").append(desc).append("\r\n\t */\r\n");
		}
		sb.append("\t").append("public ").append(type).append(" ")
		.append(key).append(";");
		sb.append("\r\n");
		return sb.toString();
	}

	protected String getFieldStr(String field, String type, String cmt) {
		StringBuilder sb = new StringBuilder();
		sb.append("\t").append("private ").append(type).append(" ")
		.append(field).append(";");
		if (cmt != null) {
			sb.append("//").append(cmt);
		}
		sb.append("\r\n");
		return sb.toString();
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	protected String getMethodStr(String field, String type) {
		StringBuilder get = new StringBuilder("\tpublic ");
		get.append(type).append(" ");
		if (type.equals("boolean")) {
			get.append(field);
		} else {
			get.append("get");
			get.append(upperFirestChar(field));
		}
		get.append("(){").append("\r\n\t\treturn this.").append(field)
		.append(";\r\n\t}\r\n");
		StringBuilder set = new StringBuilder("\tpublic void ");

		if (type.equals("boolean")) {
			set.append(field);
		} else {
			set.append("set");
			set.append(upperFirestChar(field));
		}
		set.append("(").append(type).append(" ").append(field)
		.append("){\r\n\t\tthis.").append(field).append("=")
		.append(field).append(";\r\n\t}\r\n");
		get.append(set);
		return get.toString();
	}

	protected static String upperFirestChar(String src) {
		return src.substring(0, 1).toUpperCase().concat(src.substring(1));
	}
	
	private static String getListCell(String listStr){
		int si = listStr.lastIndexOf("<")+1;
		int ei = listStr.indexOf(">");
		if(si == 0 ||ei == -1){
			return null;
		}
		String t = listStr.substring(si, ei);
		return t;
		
	}
	
	public String typeTrans(String type) {
		if(type == null){
			return null;
		}
		if(type.contains("ArrayList")){
			addImport("java.util.ArrayList");
			String t = getListCell(type);
			typeTrans(t);
			return type;
		}
		if(type.contains("List")){
			addImport("java.util.List");
			String t = getListCell(type);
			typeTrans(t);
			return type;
		}
		if(type.contains("double")||type.contains("Double")){
			return "double";
		}
		if(type.contains("long") ||type.contains("Long") ){
			return "long";
		}
		else if (type.contains("tinyint") ||type.contains("bool")) {
			return "boolean";
		} 
		else if (type.contains("int")||type.contains("Int")  
				|| type.contains("Short")||type.contains("short")) {
			return "int";
		} 
		else if (type.contains("varchar") || type.contains("date")
				|| type.contains("time") || type.contains("datetime")
				|| type.contains("timestamp") || type.contains("text")
				|| type.contains("String")
				|| type.contains("enum") || type.contains("set")) {
			return "String";
		} 
		else if (type.contains("binary") || type.contains("blob")) {
			return "byte[]";
		}
		else if(type.equals("AMF")){
			return "Object";
		}
		else {
			addImport(props.getPack() + ".datas." + type);
			return type;
		}
	}

//	protected void addWrite(CellVO cvo) {
//		if(cvo.type.contains("List")){
//			constructs.append("\t\t\tbytes.writeObject("+cvo.key+");\r\n");
//		}
//		else if(cvo.type.equals("Int") || cvo.type.equals("int")){
//			constructs.append("\t\t\tbytes.writeInt("+cvo.key+");\r\n");
//		}
//		else if(cvo.type.equals("Double") || cvo.type.equals("double")){
//			constructs.append("\t\t\tbytes.writeDouble("+cvo.key+");\r\n");
//		}
//		else if(cvo.type.equals("Short") ||  cvo.type.equals("short")){
//			constructs.append("\t\t\tbytes.writeShort("+cvo.key+");\r\n");
//		}
//		else if(cvo.type.equals("String") ||  cvo.type.equals("string")){
//			constructs.append("\t\t\tbytes.writeUTF("+cvo.key+");\r\n");
//		}
//		else if(cvo.type.equals("AMF")){
//			constructs.append("\t\t\tbytes.writeAMFObject("+cvo.key+");\r\n");
//		}else{
//			constructs.append("\t\t\tbytes.writeAMFObject("+cvo.key+");\r\n");
//		}
//	}
}
