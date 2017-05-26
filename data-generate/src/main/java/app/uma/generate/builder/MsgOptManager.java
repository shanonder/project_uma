package app.uma.generate.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.MsgOptNode;
import app.uma.generate.properties.AssetsProperties;

@Component
public class MsgOptManager {
	public ArrayList<MsgOptNode> getNodes() {
		return nodes;
	}

	private static final Logger logger = Logger.getLogger(MsgOptManager.class);
//	@Autowired
//	private GenerateVersion version;
//	@Autowired
//	private Config config;
	@Autowired
	private AssetsProperties assets;
	
	private ArrayList<MsgOptNode> nodes;
	
	public MsgOptManager() {
		nodes = new ArrayList<>();
	}
	
	@SuppressWarnings({ "unchecked" })
	public void init() throws Exception{
		String dir = assets.getProtocolMsgDir();
		File dirFile = new File(dir);
		File[] fs = dirFile.listFiles();
		for(File file:fs){
			logger.info(file.getName());
			String fileName = file.getName();
			String prefix = fileName.substring(fileName.lastIndexOf(".")+1);
			if(prefix.equals("xml")){
				SAXReader reader = new SAXReader();   
				Document doc = reader.read(file);   
				Element root = doc.getRootElement();
				root.attributeIterator();
				List<Element> items = root.elements("item");
				logger.info("protocol-msg import start...");
				for(Element item : items){
					MsgOptNode node = new MsgOptNode();
					nodes.add(node);
					node.setName(item.attributeValue("name"));
					node.setDesc(item.attributeValue("desc"));
					node.setType(item.attributeValue("type"));
					node.setCmd(item.attributeValue("cmd"));
					List<Element> props = item.elements("prop"); 
					for(Element prop : props){
						CellVO cvo = new CellVO();
						cvo.key = prop.attributeValue("name");
						cvo.type = prop.attributeValue("type");
						cvo.desc = prop.attributeValue("desc");
						node.cells.add(cvo);
					}
				}  
			}
		}
		logger.info("protocol-msg import success...");
	}

}
