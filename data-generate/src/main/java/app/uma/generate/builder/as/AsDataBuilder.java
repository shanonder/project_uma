package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.DataOptNode;

@Component
public class AsDataBuilder extends AsFileWriter{
	private static final Logger logger = Logger.getLogger(AsDataBuilder.class);
	
	
	protected StringBuilder decodes;
	protected StringBuilder encodes;
	protected StringBuilder disposes;
	protected StringBuilder writes;
	protected StringBuilder reads;
	
	
	
	public AsDataBuilder(){
		super();
		encodes = new StringBuilder();
		decodes = new StringBuilder();
		disposes = new StringBuilder();
		writes = new StringBuilder();
		reads = new StringBuilder();
	}
	
	@Override
	protected void reset(){
		super.reset();
		decodes.delete(0, decodes.length());
		encodes.delete(0, encodes.length());
		disposes.delete(0, disposes.length());
		writes.delete(0, writes.length());
		reads.delete(0, reads.length());
	}
	
	public void frush(DataOptNode node){
		String outPack = props.getPackData();
		String packageinfo = "package " + outPack +" {\r\n\r\n";
		String dir = props.getPath() + outPack.replace(".", "/") + "/";
		
		reset();
		for (String pock :props.getDataImport()){
			addImport(pock);
		}
		disposes = new StringBuilder();
		File file = new File(dir, node.getName() + ".as");
		
	
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.getDesc() != null){
			classInfo.append("\t * " +node.getDesc() + "\r\n" );
		}
		if(node.getMd5() != null){
			classInfo.append("\t * md5:" + node.getMd5() + "\r\n" );
		}
		for(CellVO cvo : node.cells){
			optCell(cvo);
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(node.getName());
		if(node.getParent() != null){
			classInfo.append(" extends ").append(node.getParent());
		}
		classInfo
//		.append(" implements ISocketData")
		.append("{\r\n");
		classInfo.append(fields);
		classInfo.append("\n\t\tpublic function " + node.getName() +"(");
		classInfo.append(params);
		classInfo.append("){\r\n")
		.append("\t\t\tsuper();\r\n")
		.append(constructs)
		.append("\t\t}\r\n");
//		function encode
		classInfo
		.append("\n\t\tpublic static function read(bytes :ByteArray , data : "+node.getName()+" = null):" + node.getName() + "{\n");
		classInfo.append("\t\t\tdata ||= new " + node.getName() + "();\n");
		if(node.getParent()!= null){
			classInfo.append("\t\t\t"+ node.getParent() + ".read(bytes , data);\n");
		}
		classInfo.append(reads);
		classInfo.append("\t\t\treturn data;\n");
		classInfo.append("\t\t}\n");
//		function decode
		classInfo
		.append("\n\t\tpublic static function write(bytes :ByteArray , data : "+node.getName()+"):ByteArray{\n");
		if(node.getParent()!= null){
			classInfo.append("\t\t\t"+ node.getParent() + ".write(bytes , data);\n");
		}
		classInfo.append(writes);
		classInfo.append("\t\t\treturn bytes;\n");
		classInfo.append("\t\t}\n");
		
		
		
		classInfo.append(methods);
		classInfo.append(disposes);
		classInfo.append("\r\n");
		classInfo.append("\t}\r\n");
		classInfo.append("}");
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(packageinfo);
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
			logger.info(node.getName() + " init success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void optCell(CellVO cvo) {
		String type = typeTrans(cvo.type);
		fields.append(getPublicFieldStr(cvo.key, type, cvo.desc));
//		writes.append(cvo,)
		addWrite(cvo,writes,"data");
		addRead(cvo,reads,"data");
	}
	
	
}
