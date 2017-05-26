package app.uma.generate.builder.java;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import app.uma.generate.builder.ICodeBuilder;
import app.uma.generate.config.GeneralBeans;
import app.uma.generate.node.DataOptNode;
import app.uma.generate.node.MsgOptNode;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;

@Component
public class JavaCodeBuilder implements ICodeBuilder {

	@Autowired
	@Qualifier(GeneralBeans.JAVA_PROPERTIES)
	private CodeProperties props;
	
	@Autowired
	private Config config;

	@Autowired
	private JavaMsgConstBuilder msgConstBuilder;
	
//	@Autowired
//	private JavaRegisterBuilder;
	
	@Autowired
	private JavaDataBuilder dataBuilder;
	
	@Autowired
	private JavaC2SBuilder c2SBuilder;
	
	@Autowired
	private JavaS2CBuilder s2CBuilder;
	
	private ArrayList<String> outDirs;
	@Override
	public ArrayList<String> getOutDirs() {
		if(outDirs == null){
			outDirs = new ArrayList<>();
			outDirs.add(props.getPath() + props.getPack().replace(".", "/") + "/");
			outDirs.add(props.getPath() + props.getPackData().replace(".", "/") + "/");
			outDirs.add(props.getPath() + props.getPackRequest().replace(".", "/") + "/");
			outDirs.add(props.getPath() + props.getPackResponse().replace(".", "/") + "/");
		}
		return outDirs;
	}

	@Override
	public void buildData(DataOptNode node) {
		dataBuilder.frush(node);
	}

	@Override
	public void buildMessage(MsgOptNode node) {
		if(node.getType().equals("request")){
			c2SBuilder.frush(node);
		}
		if(node.getType().equals("response")){
			s2CBuilder.frush(node);
		}
		msgConstBuilder.addCmd(node);
		
	}

	@Override
	public void buildOther() {
		msgConstBuilder.setMd5(config.getVersion());
		msgConstBuilder.frush();
	}

}
