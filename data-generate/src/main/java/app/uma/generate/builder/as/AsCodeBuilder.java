package app.uma.generate.builder.as;

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
public class AsCodeBuilder implements ICodeBuilder {

	
	@Autowired
	@Qualifier(GeneralBeans.AS_PROPERTIES)
	private CodeProperties props;
	
	@Autowired
	private Config config;

	@Autowired
	private AsMsgConstBuilder protocolConstBuilder;
	
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

	}
	
	@Override
	public ArrayList<String> getOutDirs() {
		if(outDirs == null){
			outDirs = new ArrayList<>();
			String path = props.getPath() + props.getPack().replace(".", "/") + "/";
			outDirs.add(path + "data/");
			outDirs.add(path + "request/");
			outDirs.add(path + "response/");
			outDirs.add(path + "consts/");
		}
		return outDirs;
	}
	
	@Override
	public void buildData(DataOptNode node) {
		asDataCodeBuilder.frush(node);
		asRegisterBuilder.addData(node.name);
	}
	
	@Override
	public void buildMessage(MsgOptNode node) {
		if(node.getType().equals("request")){
			asC2SBuilder.frush(node);
		}
		if(node.getType().equals("response")){
			asS2CBuilder.frush(node);
		}
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
