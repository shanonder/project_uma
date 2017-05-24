package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.icday.builds.CellVO;
import com.icday.utils.CreateFileUtil;

import config.Config;

public class AsDataCodeBuilder extends AsCodeFileWriter{
	private static String outDir;
	private static String outPack;
	private static String packageinfo;
	private static String dir;
	

	private static AsRegisterBuilder asRegisterBuilder;
	static {
		outPack = Config.p.getProperty("flashpack") + ".datas";
		outDir = Config.p.getProperty("flashDir");
		String t = outPack.replace(".", "/");
		dir = outDir + t + "/";
		packageinfo = "package " + outPack + "{\r\n\r\n";
		CreateFileUtil.createDir(dir);
		
	}

	protected StringBuilder decodes;
	protected StringBuilder encodes;
	protected StringBuilder disposes;
	public AsDataCodeBuilder(String codeName,String parent , ArrayList<CellVO> cells , String desc,String md5){
		super();
		disposes = new StringBuilder();
		File file = new File(dir, codeName + ".as");
		System.out.println(file.getAbsolutePath());
//		addImport("com.icday.net.interfaces.ISocketData");
		if(desc != null){
			classInfo.append("\t * md5:" + desc + "\r\n" );
		}
		classInfo.append("\t/**\r\n\t * 此类由").append(Config.APP_NAME).append("自动生成\r\n");
		if(md5 != null){
			classInfo.append("\t * md5:" + md5 + "\r\n" );
		}
		int size = cells.size();
		for(CellVO cvo : cells){
			optCell(cvo);
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(codeName);
		if(parent != null){
			classInfo.append(" extends ").append(parent);
		}
		classInfo
//		.append(" implements ISocketData")
		.append("{\r\n");
		classInfo.append(fields);
		classInfo.append("\t\tpublic function "+codeName+"(");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void optCell(CellVO cvo) {
		String type = typeTrans(cvo.type);
		fields.append(getPublicFieldStr(cvo.key, type, cvo.desc));
	}
}
