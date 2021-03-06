package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.MsgOptNode;

@Component
public class JavaC2SBuilder extends JavaFileWriter {

	public JavaC2SBuilder(){
		super();
	}
	
	public void frush(MsgOptNode node){
		reset();
		String fileName = node.getName() + "Request";
		String outPack = props.getPackRequest();
		String packageinfo = "package " + outPack + ";\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		for (String pock :props.getRequestImport()){
			addImport(pock);
		}
		File file = new File(dir, fileName + ".java");

		
		classInfo.append(imports);
		
		classInfo.append("/**\r\n * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.getMd5() != null){
			classInfo.append(" * md5:" + node.getMd5() + "\r\n" );
		}
		params.append("ClientRequest request");
		for(CellVO cvo : node.cells){
			optCell(cvo);
		}
		classInfo.append(" */\r\n");
		classInfo.append("public class ")
		.append(fileName).append("{\r\n");
		classInfo.append("\tpublic " + fileName + "(");
		classInfo.append(params);
		classInfo.append(") throws Exception{\r\n");
		classInfo.append(constructs).append("\r\n");
		classInfo.append("\t}\r\n\r\n");
		classInfo.append(fields);
		classInfo.append(methods);
		classInfo.append("\r\n");
		classInfo.append("}");
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(packageinfo);
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void optCell(CellVO cvo) {
		writeConstructs(cvo);
		String type = typeTrans(cvo.type);
		fields.append(getFieldStr(cvo.key, type, cvo.desc));
		methods.append(getMethodStr(cvo.key, type , cvo.desc));
	}

	private void writeConstructs(CellVO cvo){
		constructs.append("\t\t").append(cvo.key).append(" = ");
		if(cvo.type.equals("List")){
			constructs.append("request.getAMFObject();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("int")){
			constructs.append("request.getInt();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("long")){
			constructs.append("request.getLong();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("double")){
			constructs.append("request.getDouble();\r\n");
		}
		
		else if(cvo.type.equalsIgnoreCase("short")){
			constructs.append("request.getShort();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("string")){
			constructs.append("request.getString();\r\n");
		}
		else if(cvo.type.equals("AMF")){
			constructs.append("request.getAMFObject();\r\n");
		}else{
			logger.warn("unknown type " + cvo.type);
		}
	}
}
