package app.uma.generate.builder.as;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import app.uma.generate.builder.ICodeBuilder;
import app.uma.generate.config.GeneralBeans;
import app.uma.generate.node.DataOptNode;
import app.uma.generate.node.MessageOptNode;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;

@Component
public class AsCodeBuilder implements ICodeBuilder {

	
	@Autowired
	@Qualifier(GeneralBeans.AS_PROPERTIES)
	private CodeProperties props;
	
	@Autowired
	private Config config;

	@Autowired
	private AsProtocolConstBuilder protocolConstBuilder;
	
	@Autowired
	private AsRegisterBuilder asRegisterBuilder;
	
	@Autowired
	private AsDataCodeBuilder asDataCodeBuilder;
	
	@Autowired
	private AsC2SBuilder asC2SBuilder;
	
	@Autowired
	private AsS2CBuilder asS2CBuilder;
	
	private ArrayList<String> outDirs;
	
	public AsCodeBuilder() {
		outDirs = new ArrayList<>();
		config.getAppName();
		String pack = props.getPath();
		outDirs.add(pack + "data/");
		outDirs.add(pack + "request/");
		outDirs.add(pack + "response/");
		outDirs.add(pack + "consts/");
		
	}
	@Override
	public ArrayList<String> getOutDirs() {
		return outDirs;
	}
	
	@Override
	public void buildData(DataOptNode node) {
		asDataCodeBuilder.frush(node);
	}
	
	@Override
	public void buildMessage(MessageOptNode node) {
//		new AsMCodeBuilder(node);
		asC2SBuilder.frush(node);
		asS2CBuilder.frush(node);
		protocolConstBuilder.addCmd(node);
	}
	
	@Override
	public void buildOther() {
		protocolConstBuilder.setMd5(config.getVersion());
		protocolConstBuilder.frush();
		asRegisterBuilder.setMd5(config.getVersion());
		asRegisterBuilder.frush();
	}
	

}
