package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.icday.builds.CellVO;
import com.icday.utils.CreateFileUtil;

import config.Config;

public class AsC2SBuilder extends AsCodeFileWriter {
	private static String outDir;
	private static String outPack;
	private static String packageinfo;
	private static String dir;
	static {
		outPack = Config.p.getProperty("flashpack") + ".requests";
		outDir = Config.p.getProperty("flashDir");
		String t = outPack.replace(".", "/");
		dir = outDir + t + "/";
		packageinfo = "package " + outPack + "{\r\n\r\n";
		CreateFileUtil.createDir(dir);
	}
	public AsC2SBuilder(String codeName,String cmd, ArrayList<CellVO> cells, String md5){
		super();
		File file = new File(dir, codeName + ".as");
		System.out.println(file.getAbsolutePath());
		addImport("com.icday.net.socket.SocketRequestBase");
		addImport("com.icday.net.interfaces.INetRequest");

		classInfo.append("\t/**\r\n\t * 此类由").append(Config.APP_NAME).append("自动生成\r\n");
		if(md5 != null){
			classInfo.append("\t * md5:" + md5 + "\r\n" );
		}
		int size = cells.size();
		for(int i = 0 ; i < size ; ++i){
			CellVO cvo = cells.get(i);
			if(i == 0){
				params.append(cvo.key + ":" + typeTrans(cvo.type));
			}else{
				params.append(" , " + cvo.key + ":" + typeTrans(cvo.type));
			}
			addWrite(cvo);
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(codeName).append(" extends SocketRequestBase implements INetRequest{\r\n");
		classInfo.append("\t\tpublic function "+codeName+"(");
		classInfo.append(params);
		classInfo.append("){\r\n")
		.append("\t\t\tsuper("+cmd + ");\r\n")
		.append(constructs)
		.append("\t\t}");
		classInfo.append(fields);
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
 
}
