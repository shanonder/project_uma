package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.MsgOptNode;

@Component
public class AsS2CBuilder extends AsFileWriter{

	public AsS2CBuilder(){
		super();
	}
	
	public void frush(MsgOptNode node){
		reset();
		String outPack = props.getPackResponse();
		String packageinfo = "package " + outPack +" {\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String codeName = node.getName() + "Response";
		for (String pock :props.getResponseImport()){
			addImport(pock);
		}
		
		File file = new File(dir, codeName + ".as");
		addImport("flash.utils.ByteArray");
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.getMd5() != null){
			classInfo.append("\t * md5:" + node.getMd5() + "\r\n" );
		}
		params.append("bytes:ByteArray");
		for(CellVO cvo : node.cells){
			optCell(cvo);
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(codeName).append("{\r\n");
		classInfo.append(fields);
		classInfo.append("\t\tpublic function "+codeName+"(");
		classInfo.append(params);
		classInfo.append("){\r\n")
		.append(constructs)
		.append("\t\t}\r\n");
		
		classInfo.append(methods);
		classInfo.append("\r\n");
		classInfo.append("\t}\r\n");
		classInfo.append("}");
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(packageinfo);
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
			logger.info(codeName + " init success...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void optCell(CellVO cvo) {
		writeConstructs(cvo);
		String type = typeTrans(cvo.type);
		fields.append(getPublicFieldStr(cvo.key, type, cvo.desc));
	}
	
	private void writeConstructs(CellVO cvo){
		constructs.append("\t\t\t").append(cvo.key).append(" = ");
		if(cvo.type.equalsIgnoreCase("Array")){
			constructs.append("bytes.readObject();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("int")){
			constructs.append("bytes.readInt();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("long")){
			constructs.append("bytes.readDouble();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("double")){
			constructs.append("bytes.readDouble();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("short")){
			constructs.append("bytes.readShort();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("String")){
			constructs.append("bytes.readUTF();\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("AMF")){
			constructs.append("bytes.readObject();\r\n");
		}else{
			constructs.append("bytes.readObject();\r\n");
		}
	}
}
