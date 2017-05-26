package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.DataOptNode;

@Component
public class AsDataCodeBuilder extends AsCodeFileWriter{
	private static final Logger logger = Logger.getLogger(AsDataCodeBuilder.class);
	
	
	protected StringBuilder decodes;
	protected StringBuilder encodes;
	protected StringBuilder disposes;
	
	public AsDataCodeBuilder(){
		super();
		encodes = new StringBuilder();
		decodes = new StringBuilder();
		disposes = new StringBuilder();
	}
	
	@Override
	protected void reset(){
		super.reset();
		decodes.delete(0, decodes.length());
		encodes.delete(0, encodes.length());
		disposes.delete(0, disposes.length());
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
		File file = new File(dir, node.name + ".as");
		
	
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.desc != null){
			classInfo.append("\t * " +node.desc + "\r\n" );
		}
		if(node.md5 != null){
			classInfo.append("\t * md5:" + node.md5 + "\r\n" );
		}
		for(CellVO cvo : node.cells){
			optCell(cvo);
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(node.name);
		if(node.parent != null){
			classInfo.append(" extends ").append(node.parent);
		}
		classInfo
//		.append(" implements ISocketData")
		.append("{\r\n");
		classInfo.append(fields);
		classInfo.append("\t\tpublic function "+node.name+"(");
		classInfo.append(params);
		classInfo.append("){\r\n")
		.append("\t\t\tsuper();\r\n")
		.append(constructs)
		.append("\t\t}\r\n");
//		function encode
		
//		function decode
		
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
			logger.info(node.name + " init success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void optCell(CellVO cvo) {
		String type = typeTrans(cvo.type);
		fields.append(getPublicFieldStr(cvo.key, type, cvo.desc));
	}
}
