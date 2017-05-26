package app.uma.generate.builder;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import app.uma.generate.builder.as.AsCodeBuilder;
import app.uma.generate.builder.java.JavaCodeBuilder;
import app.uma.generate.node.DataOptNode;
import app.uma.generate.node.MsgOptNode;
import app.uma.generate.properties.Config;
import app.uma.utils.CreateFileUtil;

@Component
public class GenerateBuilder {

	@Autowired
	private Config config;

	@Autowired
	private ApplicationContext context;


	@Autowired
	private DataOptManager dataOptManger;
	
	@Autowired
	private MsgOptManager msgOptManager;
	
	
	
	@Autowired
	private AsCodeBuilder asCodeBuilder;
	
	@Autowired
	private JavaCodeBuilder javaCodeBuilder;

	private ArrayList<ICodeBuilder> builders;
	public GenerateBuilder(){
		builders = new ArrayList<>();
	}

	public void init(){
		regist(asCodeBuilder);
		regist(javaCodeBuilder);
	}


	private void regist(ICodeBuilder builder){
		builders.add(builder);
	}

	public void execute() throws Exception{
		for (ICodeBuilder builder : builders) {
			ArrayList<String> list = builder.getOutDirs();
			if(list != null){
				for (String dir : list){
					CreateFileUtil.createDir(dir);
				}
			}
		}
		HashManager version = context.getBean(HashManager.class);
		config.getAppName();
		version.init();
		dataOptManger.init();
		for(DataOptNode node:dataOptManger.getNodes()){
			for (ICodeBuilder builder : builders) {
				builder.buildData(node);
			}
		}
		
		msgOptManager.init();
		for(MsgOptNode node:msgOptManager.getNodes()){
			for (ICodeBuilder builder : builders) {
				builder.buildMessage(node);
			}
		}
		
		for (ICodeBuilder builder : builders) {
			builder.buildOther();
		}

		version.flush();
	}



	public void dispose() {

	}
}
