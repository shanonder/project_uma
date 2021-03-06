package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.MsgOptNode;

@Component
public class JavaS2CBuilder extends JavaFileWriter {

	private StringBuilder constrDescs;
	public JavaS2CBuilder(){
		super();
		constrDescs = new StringBuilder();
	}
	
	@Override
	protected void reset(){
		super.reset();
		constrDescs.delete(0, constrDescs.length());
	}
	
	private void optCell(CellVO cvo) {
		// TODO Auto-generated method stub
		writeConstructs(cvo);
//		String type = typeTrans(cvo.type);
//		fields.append(getFieldStr(cvo.key, type, cvo.desc));
//		methods.append(getMethodStr(cvo.key, type));
	}

	
	private void writeConstructs(CellVO cvo){
		constructs.append("\t\t");
		if(cvo.type.contains("[]")){
			constructs.append("output.writeArrayList(" + cvo.key + ");\r\n");
		}
		else if(cvo.type.contains("List")){
			constructs.append("output.writeArrayList(" + cvo.key + ");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("int")){
			constructs.append("output.writeInt(");
			constructs.append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("long")){
			constructs.append("output.writeLong(");
			constructs.append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("double")){
			constructs.append("output.writeDouble(");
			constructs.append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("short")){
			constructs.append("output.writeShort(");
			constructs.append(cvo.key).append(");\r\n");
		}
		else if(cvo.type.equalsIgnoreCase("string")){
			constructs.append("output.writeUTF(");
			constructs.append(cvo.key).append(" == null ? ").append("\"\" : ").append(cvo.key).append(");\r\n");
		}
		else{
			constructs.append("output.writeData(");
			constructs.append(cvo.key).append(" == null ? ").append("new Object() : ").append(cvo.key).append(");\r\n");
		}
		
	}

	public void frush(MsgOptNode node) {
		reset();
		String fileName = node.getName() + "Response";
		// TODO Auto-generated method stub
		String outPack = props.getPackResponse();
		String packageinfo = "package " + outPack + ";\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		for (String pock :props.getResponseImport()){
			addImport(pock);
		}
		
		File file = new File(dir, fileName + ".java");
		constrDescs.append("\t * @struct int 状态  200 正常\n");
		params.append("int status ");
		for(CellVO cvo : node.cells){
			params.append(", ").append(typeTrans(cvo.type)).append(" ").append(cvo.key);
			constrDescs.append("\t * @" + cvo.key + " " + cvo.type + " " + (cvo.desc != null?cvo.desc:"") + "\n") ; 
			optCell(cvo);
		}
		classInfo.append(imports);
		classInfo.append("/**\r\n * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.getMd5() != null){
			classInfo.append(" * md5:" + node.getMd5() + "\r\n" );
		}
		classInfo.append(" */\r\n");
		classInfo.append("public class ")
		.append(fileName).append("  extends ServerResponse").append("{\r\n");
//		constructDesc
		classInfo.append("\n\t/**\n");
		classInfo.append(constrDescs);
		classInfo.append("\t */\n");
//		endconstructDesc
		classInfo.append("\tpublic "+ fileName +"(");
		classInfo.append(params);
		classInfo.append(") throws Exception{\r\n");
		classInfo.append("\t\tsuper("+node.getCmd() + " , status);\r\n");
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
}
