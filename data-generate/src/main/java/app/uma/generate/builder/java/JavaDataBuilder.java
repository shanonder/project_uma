package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.DataOptNode;

@Component
public class JavaDataBuilder extends JavaFileWriter{
	
	private StringBuilder writes;
	private StringBuilder reads;

	public JavaDataBuilder(){
		super();
		writes = new StringBuilder();
		reads = new StringBuilder();
	}

	private void optCell(CellVO cvo) {
		String type = typeTrans(cvo.type);
		fields.append(getFieldStr(cvo.key, type, cvo.desc));
		methods.append(getMethodStr(cvo.key, type));
		addWrite(cvo,writes,"data");
		addRead(cvo,reads,"data");
	}
	
	public void reset(){
		super.reset();
		writes.delete(0, writes.length());
		reads.delete(0, reads.length());
	}

	public void frush(DataOptNode node) {
		reset();
		String outPack = props.getPackData();
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		String packageinfo = "package " + outPack + ";\r\n\r\n";
		for (String pock :props.getDataImport()){
			addImport(pock);
		}

		String codeName = upperFirestChar(node.getName());
		File file = new File(dir, codeName + ".java");
		addImport("java.io.Serializable");
		addImport("java.io.DataOutputStream");
		addImport("java.io.DataInputStream");
		for(CellVO cvo : node.cells){
			optCell(cvo);
		}
		classInfo.append(imports);
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.getMd5() != null){
			classInfo.append("\t * md5:" + node.getMd5() + "\r\n" );
		}
		classInfo.append("\t */\r\n");
		classInfo.append("public class ").append(codeName);
		if(node.getParent() != null){
			classInfo.append(" extends ").append(node.getParent());
		}
		classInfo.append(" implements Serializable{\r\n");
		classInfo.append("\tprivate static final long serialVersionUID = 1L;\r\n");
		classInfo.append(fields);
		classInfo.append(methods);
//		=======================write==========================
		classInfo.append("\n\tpublic static void write(DataOutputStream out , "+ codeName + " data) throws Exception{\n");
		if(node.getParent() != null){
			classInfo.append("\t\t" + node.getParent() + ".write( out , data );\n");
		}
		classInfo.append(writes);
		classInfo.append("\t}\n");
//		=======================read==========================
		classInfo.append("\n\t@SuppressWarnings(\"unchecked\")\n");
		classInfo.append("\tpublic static " + codeName + " read(DataInputStream in , "+ codeName +" data) throws Exception{\n");
		classInfo.append("\t\tif(data == null){\n\t\t\tdata = new " + codeName + "();\n\t\t}\n");
		
		if(node.getParent() != null){
			classInfo.append("\t\t" + node.getParent() + ".read( in , data );\n");
		}
		classInfo.append(reads);
		classInfo.append("\t\treturn data;\n");
		classInfo.append("\t}\n");
		
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
