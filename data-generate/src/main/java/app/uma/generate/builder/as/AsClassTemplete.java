package app.uma.generate.builder.as;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.xmlbeans.impl.tool.Extension.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import app.uma.generate.config.GeneralBeans;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;

public class AsClassTemplete {
	
	private static final Logger log = LoggerFactory.getLogger(AsClassTemplete.class);

	@Autowired
	protected Config config;
	
	@Autowired
	@Qualifier(GeneralBeans.AS_PROPERTIES)
	protected CodeProperties props;
	
	protected StringBuilder classInfo;
	protected StringBuilder imports;
	protected StringBuilder constructs;
	protected StringBuilder fields;
	protected StringBuilder methods;
	protected StringBuilder params;
	
	private String name;
	private String pack;
	private String desc;
	
	
	private ArrayList<String> importArray;

	private String hash;

	private ArrayList<String> interfaces;
	private ArrayList<param> paramArray;
	private String overrideClass;
	
	public AsClassTemplete(String name ,String pack, String desc ,String hash,String overrideClass , ArrayList<String> interfaces ){
		importArray = new ArrayList<>();
		paramArray = new ArrayList<>();
		construct = new Construct();
		this.name = name;
		this.pack = pack;
		this.desc = desc;
		this.hash = hash;
		this.overrideClass = overrideClass;
		this.interfaces = interfaces;

		if(overrideClass != null){
			addImport(overrideClass);
		}
		
		if(interfaces != null){
			for(String intface : interfaces){
				addImport(intface);
			}
		}
	}
	
	public void addImport(String pack){
		if(!importArray.contains(pack)){
			importArray.add(pack);
		}
	}
	
	private Construct construct;
	public void setConstuct(ArrayList<Param> params){
		construct = new Construct();
		construct.params = params;
	}
	
	public void addParam(String scopes , String name , String type , String desc  ){
		addParam(scopes,name,type,desc,false);
	}
	
	public void addParam(String scopes , String name , String type , String desc ,Boolean isOverride ){
		param p = new param();
		p.scopes = scopes;
		p.name = name;
		p.type = type;
		p.desc = desc;
		p.isOverride = isOverride;
		paramArray.add(p);
	}
	
	public void addConsturct(String line){
		
	}
	
	public void addMethod(String scopes, String name , String type ,ArrayList<Param> params, String reType, String desc ){
		
	}
	
	
	public StringBuilder frush(){
		String packageinfo = "package " + pack +" {\r\n\r\n";
		packageinfo = "package " + pack + "{\r\n\r\n";
		classInfo.append("\t/**\r\n\t * 此类由").append(config.getAppName()).append("自动生成\r\n");
		if(hash != null){
			classInfo.append("\t * md5:" + hash + "\r\n" );
		}
		writeConstruct();
		classInfo.append("\t */\r\n");
		classInfo.append(imports);
		classInfo.append("\tpublic class ")
		.append(name);
//		if(overrideClass){
//			
//		}
		classInfo
		.append("{\r\n");
		classInfo.append(fields);
		classInfo.append(methods);
		classInfo.append("\r\n");
		classInfo.append("\t}\r\n");
		classInfo.append("}");
		try {
			String dir = props.getPath() + pack.replace(".", "/") + "/";
			File file = new File(dir, name + ".as");
			FileWriter fw = new FileWriter(file);
			fw.write(packageinfo);
			fw.write(classInfo.toString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classInfo;
	}

	private void writeConstruct() {
		
	}
	
}

class param{
	public Boolean isOverride;
	String scopes;
	String name;
	String type;
	String desc;
}

class Construct{
	ArrayList<Param> params;
	ArrayList<String> lines;
	public Construct(){
		params = new ArrayList<>();
		lines = new ArrayList<>();
	}
}

class method{
	String scopes;
	String name;
	String type;
	String desc;
	ArrayList<Param> params;
	ArrayList<String> lines;
	public method(){
		params = new ArrayList<>();
		lines = new ArrayList<>();
	}
	public void addLine(String line){
		lines.add(line);
	}
}