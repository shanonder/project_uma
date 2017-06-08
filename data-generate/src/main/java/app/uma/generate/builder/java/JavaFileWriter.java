package app.uma.generate.builder.java;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import app.uma.generate.config.GeneralBeans;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;

public class JavaFileWriter {
	@Autowired
	protected Config config;
	
	@Autowired
	@Qualifier(GeneralBeans.JAVA_PROPERTIES)
	protected CodeProperties props;
	
	public JavaFileWriter(){
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

	protected void reset(){
		classInfo.delete(0, classInfo.length());
		imports.delete(0, imports.length());
		constructs.delete(0, constructs.length());
		fields.delete(0, fields.length());
		methods.delete(0, methods.length());
		params.delete(0, params.length());
		if(importList != null){
			importList.clear();
		}
	}
	
	protected static final Logger logger = Logger.getLogger(JavaFileWriter.class);

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
		int ei = listStr.indexOf("]");
		if(ei == -1){
			return null;
		}
		String t = listStr.substring(ei+1,listStr.length());
		return t;
	}
	
	public String typeTrans(String type,Boolean referenceType) {
		if(type == null){
			return null;
		}
		if(type.contains("[]")){
			addImport("java.util.ArrayList");
			String t = getListCell(type);
			t = typeTrans(t,true);
			return "ArrayList<" + t + ">";
		}
		
		if(type.contains("double")||type.contains("Double")){
			return referenceType?"Double":"double";
		}
		if(type.contains("long") ||type.contains("Long") ){
			return referenceType?"Long":"long";
		}
		else if (type.contains("tinyint") ||type.contains("bool")) {
			return referenceType?"Boolean":"boolean";
		} 
		else if (type.contains("int")||type.contains("Int")  
				|| type.contains("Short")||type.contains("short")) {
			return referenceType?"Integer":"int";
		} 
		else if (type.contains("varchar") || type.contains("date")
				|| type.contains("time") || type.contains("datetime")
				|| type.contains("timestamp") || type.contains("text")
				|| type.contains("String") || type.contains("string")
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
			addImport(props.getPackData()+ "." + type);
			return type;
		}
	}
	public String typeTrans(String type) {
		return typeTrans(type, false);
	}

}
