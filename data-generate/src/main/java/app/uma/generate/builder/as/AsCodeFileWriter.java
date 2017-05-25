package app.uma.generate.builder.as;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import app.uma.generate.config.GeneralBeans;
import app.uma.generate.node.CellVO;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;

public class AsCodeFileWriter {
	public AsCodeFileWriter(){
		init();
	}

	@Autowired
	protected Config config;
	
	@Autowired
	@Qualifier(GeneralBeans.AS_PROPERTIES)
	protected CodeProperties props;

	private void init() {
		classInfo = new StringBuilder();
		imports = new StringBuilder();
		params = new StringBuilder();
		constructs = new StringBuilder();
		fields = new StringBuilder();
		methods = new StringBuilder();
	}

	protected static final Logger logger = Logger.getLogger(AsCodeFileWriter.class);

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
			imports.append("\timport "+ text + ";\n");
		}
	}
	protected void addWrite(CellVO cvo) {
		if(cvo.type.equals("List")){
			constructs.append("\t\t\tbytes.writeObject("+cvo.key+");\r\n");
		}
		else if(cvo.type.equals("Int") || cvo.type.equals("int")){
			constructs.append("\t\t\tbytes.writeInt("+cvo.key+");\r\n");
		}
		else if(cvo.type.equals("Double") || cvo.type.equals("double")){
			constructs.append("\t\t\tbytes.writeDouble("+cvo.key+");\r\n");
		}
		else if(cvo.type.equals("Short") ||  cvo.type.equals("short")){
			constructs.append("\t\t\tbytes.writeShort("+cvo.key+");\r\n");
		}
		else if(cvo.type.equals("String") ||  cvo.type.equals("string")){
			constructs.append("\t\t\tbytes.writeUTF("+cvo.key)
			.append(" == null ?").append("\"\" : ").append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equals("AMF")){
			constructs.append("\t\t\tbytes.writeObject("+cvo.key+");\r\n");
		}else{
			logger.warn("unknown type " + cvo.type);
		}
	}

	protected Object getPublicFieldStr(String key, String type, String desc) {
		StringBuilder sb = new StringBuilder();
		if (desc != null) {
			sb.append("\t\t/**\r\n\t\t * ").append(desc).append("\r\n\t\t */\r\n");
		}
		sb.append("\t\t").append("public var ").append(key).append(":")
		.append(type).append(";");
		sb.append("\r\n");
		return sb.toString();
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
			getListCell(type);
//			typeTrans(t);
			return "Array";
		}
		if(type.contains("List")){
			getListCell(type);
//			typeTrans(t);
			return "Array";
		}
		if(type.contains("double")||type.contains("Double")){
			return "Number";
		}
		if(type.contains("long") ||type.contains("Long") ){
			return "Number";
		}
		if(type.contains("Short") ||type.contains("short") ){
			return "int";
		}
		if (type.contains("tinyint") ||type.contains("bool")) {
			return "boolean";
		} else if (type.contains("int")||type.contains("Int")) {
			return "int";
		} else if (type.contains("varchar") || type.contains("date")
				|| type.contains("time") || type.contains("datetime")
				|| type.contains("timestamp") || type.contains("text")
				|| type.contains("String")
				|| type.contains("enum") || type.contains("set")) {
			return "String";
		} else if (type.contains("binary") || type.contains("blob")) {
			return "ByteArray";
		} else if(type.equals("AMF")){
			return "Object";
		}
		else {
			addImport(props.getPack() + ".datas." + type);
			return type;
		}
	}
}
