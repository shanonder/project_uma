package app.uma.generate.builder.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.utils.CreateFileUtil;
import app.uma.utils.StringUtil;

@Component
public class JavaExcelBuilder extends JavaFileWriter{
	
	public void createDir() {
		String dir = props.getPath() + props.getPackXls().replace(".", "/") + "/";
		CreateFileUtil.createDir(dir);
	}
	
	public void frush(String codeName,ArrayList<CellVO> cells , String md5){
		reset();
		codeName = StringUtil.UpperCaseFirst(codeName) + "Cfg";
		String dir = props.getPathExcel() + props.getPackXls().replace(".", "/") + "/";
		String packageinfo = "package " + props.getPackXls() + ";\r\n\r\n";
		CreateFileUtil.createDir(dir);
		File file = new File(dir, codeName + ".java");
		classInfo = new StringBuilder();
		imports = new StringBuilder();
		fields = new StringBuilder();
		methods = new StringBuilder();
		classInfo.append("\t/**\r\n\t * 此类由ExcelToCodeTool自动生成\r\n");
		if(md5 != null){
			classInfo.append("\t * md5:" + md5 + "\r\n" );
		}
		for(CellVO cvo : cells){
			optCell(cvo);
		}
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("public class ")
		.append(codeName).append("{\r\n");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void optCell(CellVO cvo) {
		//build import todo 
		//build fields
		String type = typeTrans(cvo.type);
		fields.append(getFieldStr(cvo.key, type, cvo.desc));
		methods.append(getMethodStr(cvo.key, type , cvo.desc));
//		fields.append(getPublicFieldStr(cvo.key, type, cvo.desc));
	}
}
