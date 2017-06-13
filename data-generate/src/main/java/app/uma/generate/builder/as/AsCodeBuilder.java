package app.uma.generate.builder.as;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import app.uma.generate.builder.HashManager;
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
	private AsMsgConstBuilder msgConstBuilder;
	
	@Autowired
	private AsRegisterBuilder asRegisterBuilder;
	
	@Autowired
	private AsDataBuilder asDataBuilder;
	
	@Autowired
	private AsC2SBuilder asC2SBuilder;
	
	@Autowired
	private AsS2CBuilder asS2CBuilder;
	
	@Autowired
	private HashManager hashManager;
	
	@Autowired
	private AsDataHashBuilder asDataHashBuilder;
	
	private ArrayList<String> outDirs;
	
	public AsCodeBuilder() {

	}
	
	@Override
	public ArrayList<String> getOutDirs() {
		if(outDirs == null){
			outDirs = new ArrayList<>();
			outDirs.add(props.getPath() + props.getPack().replace(".", "/") + "/");
			outDirs.add(props.getPath() + props.getPackData().replace(".", "/") + "/");
			outDirs.add(props.getPath() + props.getPackRequest().replace(".", "/") + "/");
			outDirs.add(props.getPath() + props.getPackResponse().replace(".", "/") + "/");
			outDirs.add(props.getPath() + props.getPackConst().replace(".", "/") + "/");
		}
		return outDirs;
	}
	
	@Override
	public void buildData(DataOptNode node) {
		hashManager.update(node.getName(), node.getMd5());
		asDataBuilder.frush(node);
		asRegisterBuilder.addData(node.getName());
		asDataHashBuilder.addClass(node);
	}
	
	@Override
	public void buildMessage(MsgOptNode node) {
		hashManager.update(node.getName(), node.getMd5());
		if(node.getType().equals("request")){
			asC2SBuilder.frush(node);
		}
		if(node.getType().equals("response")){
			asS2CBuilder.frush(node);
		}
		msgConstBuilder.addCmd(node);
	}
	
	@Override
	public void buildOther() {
		msgConstBuilder.setMd5(config.getVersion());
		msgConstBuilder.frush();
		asRegisterBuilder.setMd5(config.getVersion());
		asRegisterBuilder.frush();
		asDataHashBuilder.setMd5(config.getVersion());
		asDataHashBuilder.frush();
	}
	

}
