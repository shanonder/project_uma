package app.uma.generate.builder.as;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import app.uma.generate.config.GeneralBeans;
import app.uma.generate.node.CellVO;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;
@Component
public class AsFileWriter {
	public AsFileWriter(){
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

	protected static final Logger logger = Logger.getLogger(AsFileWriter.class);

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
	
	protected void addWrite(CellVO cvo) {
		addWrite(cvo, constructs, null);
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

	public String typeTrans(String type) {
		if(type == null){
			return null;
		}
		if(type.contains("[]")){
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
//		if(type.contains("String") ||type.contains("string") ){
//			return "String";
//		}
		if (type.contains("tinyint") ||type.contains("bool")) {
			return "boolean";
		} else if (type.contains("int")||type.contains("Int")) {
			return "int";
		} else if (type.contains("varchar") || type.contains("date")
				|| type.contains("time") || type.contains("datetime")
				|| type.contains("timestamp") || type.contains("text")
				|| type.contains("String") ||type.contains("string")
				|| type.contains("enum") || type.contains("set")) {
			return "String";
		} else if (type.contains("binary") || type.contains("blob")) {
			return "ByteArray";
		} else if(type.equals("AMF")){
			return "Object";
		}
		else {
			addImport(props.getPackData() + "." + type);
			return type;
		}
	}
	

	protected void addRead(CellVO cvo, StringBuilder builder ,String target){
		if(target == null ||target.equals("")){
			target = "";
		}else{
			target = target + ".";
		}
		builder.append("\t\t\t").append(target).append(cvo.key).append(" = ");
		if(cvo.type.equalsIgnoreCase("Array")){
			builder.append("bytes.readObject();\r\n");
		}
		else if(cvo.type.contains("[]")){//todo
			builder.append("bytes.readObject();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("int")){
			builder.append("bytes.readInt();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("long")){
			builder.append("bytes.readDouble();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("double")){
			builder.append("bytes.readDouble();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("short")){
			builder.append("bytes.readShort();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("String")){
			builder.append("bytes.readUTF();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("AMF")){
			builder.append("bytes.readObject();\r\n");
		}else{
			logger.warn("unknown type " + cvo.type);
			builder.append("bytes.readObject();\r\n");
		}
	}
	
	
	protected void addWrite(CellVO cvo , StringBuilder builder ,String target) {
		if(target == null || target.equals("")){
			target = "";
		}else{
			target = target + ".";
		}
		
		if(cvo.type.equals("Array")){
			builder.append("\t\t\tbytes.writeObject(" + target + cvo.key+");\r\n");
		}
		
		else if(cvo.type.contains("[]")){//todo
			builder.append("\t\t\tbytes.writeObject(" + target + cvo.key+");\r\n");
		}
		
		else if(cvo.type.equalsIgnoreCase("int")){
			builder.append("\t\t\tbytes.writeInt(" + target + cvo.key+");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("double")){
			builder.append("\t\t\tbytes.writeDouble(" + target +cvo.key+");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("long")){
			builder.append("\t\t\tbytes.writeDouble(" + target +cvo.key+");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("Short")){
			builder.append("\t\t\tbytes.writeShort(" + target +cvo.key+");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("String")){
			builder.append("\t\t\tbytes.writeUTF(" + target +cvo.key)
			.append(" == null ?").append("\"\" : ").append(target  + cvo.key).append(");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("AMF")){
			builder.append("\t\t\tbytes.writeObject(" + target +cvo.key+");\r\n");
		}else{
			logger.warn("unknown type " + cvo.type);
			builder.append("\t\t\tbytes.writeObject(" + target +cvo.key+");\r\n");
		}
	}
}
