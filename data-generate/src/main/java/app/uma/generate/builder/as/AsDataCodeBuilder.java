package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import app.uma.generate.builder.DataOptManager;
import app.uma.generate.config.GeneralBeans;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;
import app.uma.generate.vo.CellVO;
import app.uma.generate.vo.DataOptNode;

public class AsDataCodeBuilder extends AsCodeFileWriter{
	private static final Logger logger = Logger.getLogger(AsDataCodeBuilder.class);
	
	private String packageinfo;
	protected StringBuilder decodes;
	protected StringBuilder encodes;
	protected StringBuilder disposes;
	
	public AsDataCodeBuilder(DataOptNode node){
		super();
		
		packageinfo = "package " + props.getPack() +".data {\r\n\r\n";
		disposes = new StringBuilder();
		File file = new File(props.getPath() + props.getPack().replace(".", "/") + "/", node.name + ".as");
		
		if(node.desc != null){
			classInfo.append("\t * md5:" + node.desc + "\r\n" );
		}
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(node.md5 != null){
			classInfo.append("\t * md5:" + node.md5 + "\r\n" );
		}
		int size = node.cells.size();
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
			logger.info(node.name + "init success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void optCell(CellVO cvo) {
		String type = typeTrans(cvo.type);
		fields.append(getPublicFieldStr(cvo.key, type, cvo.desc));
	}
}
