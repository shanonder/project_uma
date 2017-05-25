package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.MessageOptNode;

public class AsS2CBuilder extends AsCodeFileWriter{

	public AsS2CBuilder(){
		super();
	}
	
	public void frush(MessageOptNode node){
		String outPack = props.getPack() + ".responses";
		String packageinfo = "package " + outPack +" {\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String codeName = node.getName() + "Response";
		for (String pock :props.getRequestImport()){
			addImport(pock);
		}
		
		File file = new File(dir, codeName + ".as");
		System.out.println(file.getAbsolutePath());
		addImport("flash.utils.ByteArray");
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.getMd5() != null){
			classInfo.append("\t * md5:" + node.getMd5() + "\r\n" );
		}
		params.append("bytes:ByteArray");
		for(CellVO cvo : node.s2cCells){
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
		if(cvo.type.equals("List")){
			constructs.append("bytes.readObject();\r\n");
		}
		else if(cvo.type.equals("Int") || cvo.type.equals("int")){
			constructs.append("bytes.readInt();\r\n");
		}
		else if(cvo.type.equals("Double") || cvo.type.equals("double")){
			constructs.append("bytes.readDouble();\r\n");
		}
		else if(cvo.type.equals("Short") ||  cvo.type.equals("short")){
			constructs.append("bytes.readShort();\r\n");
		}
		else if(cvo.type.equals("String") ||  cvo.type.equals("string")){
			constructs.append("bytes.readUTF();\r\n");
		}
		else if(cvo.type.equals("AMF")){
			constructs.append("bytes.readObject();\r\n");
		}else{
			constructs.append("bytes.readObject();\r\n");
		}
	}
}
