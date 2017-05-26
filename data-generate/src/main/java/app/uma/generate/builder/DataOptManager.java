package app.uma.generate.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.generate.node.CellVO;
import app.uma.generate.node.DataOptNode;
import app.uma.generate.properties.AssetsProperties;
import app.uma.generate.properties.Config;
import app.uma.utils.MD5Util;


@Component
public class DataOptManager {

	private static final Logger logger = Logger.getLogger(DataOptManager.class);
	@Autowired
	private AtomicInteger counter;

	@Autowired
	private HashManager version;
	@Autowired
	private Config config;
	@Autowired
	private AssetsProperties assets;

	private ArrayList<DataOptNode> nodes;
	public DataOptManager(){
		nodes = new ArrayList<>();
	}


	public ArrayList<DataOptNode> getNodes() {
		return nodes;
	}


	@SuppressWarnings("unused")
	private String getCellStringValue(Cell cell) {
		if(cell == null){
			return null;
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue(); 
	}

//	private String getCellStringValue(Row row,int index) {
//		Cell cell = row.getCell(index);
//		if(cell == null){
//			return "";
//		}
//		cell.setCellType(CellType.STRING);
//		return cell.getStringCellValue();
//	}

	@SuppressWarnings("unchecked")
	public void init() throws Exception{
		String fileName = "data.xml";
		String path = assets.getProtocolDataPath() + fileName;
		File file = new File(path);
		String hash = MD5Util.getMd5ByFile(file);
		String oVer = version.get(fileName);
		if(config.isGenerateAll() == false && oVer.equals(hash)){
			logger.info("File No Change:" + fileName);
			return;
		}
		version.update(fileName, hash);
//		Workbook book =  WorkbookFactory.create(file);
		SAXReader reader = new SAXReader();   
		Document doc = reader.read(file);   
		Element root = doc.getRootElement();
		List<Element> items = root.elements("item");
		logger.info("protocol-data import start...");
		for(Element item : items){
			DataOptNode node = new DataOptNode();
			nodes.add(node);
			node.setName(item.attributeValue("name"));
			node.setDesc(item.attributeValue("desc"));
			node.setParent(item.attributeValue("parent"));
			node.setMd5(hash);
			node.setDataId(counter.incrementAndGet());
			List<Element> props = item.elements("prop"); 
			for(Element prop : props){
				CellVO cvo = new CellVO();
				cvo.key = prop.attributeValue("name");
				cvo.type = prop.attributeValue("type");
				cvo.desc = prop.attributeValue("desc");
				node.cells.add(cvo);
			}
		}  
		logger.info("protocol-data import success...");
	}
}
