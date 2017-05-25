package app.uma.generate.builder.as;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import app.uma.generate.builder.ICodeBuilder;
import app.uma.generate.config.GeneralBeans;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;
import app.uma.generate.vo.DataOptNode;
import app.uma.generate.vo.MessageOptNode;

@Component
public class AsCodeBuilder implements ICodeBuilder {

	
	@Autowired
	@Qualifier(GeneralBeans.AS_PROPERTIES)
	private CodeProperties props;
	
	@Autowired
	private Config config;

	private ArrayList<String> outDirs;
	
	public AsCodeBuilder() {
		outDirs = new ArrayList<>();
		this.config.getAppName();
		String pack = props.getPath();
		outDirs.add(pack + "data/");
		outDirs.add(pack + "request/");
		outDirs.add(pack + "response/");
	}
	@Override
	public ArrayList<String> getOutDirs() {
		return outDirs;
	}
	
	@Override
	public void buildData(DataOptNode node) {
		new AsDataCodeBuilder(node);
	}
	
	@Override
	public void buildMessage(MessageOptNode node) {
//		new AsMCodeBuilder(node);
	}
	
	@Override
	public void buildOther() {
		// TODO Auto-generated method stub
		
	}
	

}
