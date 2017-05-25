package app.uma.generate.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
	
	private ArrayList<DataOptNode> nodes;
	public DataOptManager(){
		nodes = new ArrayList<>();
	}

	
	
	private DataOptNode node;

	public void insert(String name, String desc, String hash) {
		node = new DataOptNode();
		node.name = name;
		node.desc = desc;
		node.md5 = hash;
		node.dataId = counter.incrementAndGet();
		getNodes().add(node);
	}

	public DataOptNode getNode() {
		return node;
	}

	public ArrayList<DataOptNode> getNodes() {
		return nodes;
	}


	public void setNode(DataOptNode node) {
		this.node = node;
	}

	public void operate() {
		
	}
	

	@Autowired
	private GenerateVersion version;
	@Autowired
	private Config config;
	
	@Autowired
	private AssetsProperties assets;
	
	
	
	@SuppressWarnings("unused")
	private String getCellStringValue(Cell cell) {
		if(cell == null){
			return null;
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue(); 
	}
	
	private String getCellStringValue(Row row,int index) {
		Cell cell = row.getCell(index);
		if(cell == null){
			return "";
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue();
	}
	
	public void init() throws Exception{
		String fileName = "data.xls";
		String path = assets.getProtocolXlsxPath() + fileName;
		File file = new File(path);
		String hash = MD5Util.getMd5ByFile(file);
		String oVer = version.get(fileName);
		if(config.isGenerateAll() == false && oVer.equals(hash)){
			logger.info("File No Change:" + fileName);
			return;
		}
		version.update(fileName, hash);
//		InputStream fileInStream = new FileInputStream(file);
		Workbook book =  WorkbookFactory.create(file);
		
		int size = book.getNumberOfSheets();
		for(int i = 0 ; i < size ; ++i){
			Sheet sheet = book.getSheetAt(i);
			int length = sheet.getLastRowNum() + 1;
			for(int j = 0 ; j < length ; ++j){
				Row rowKey = sheet.getRow(j);
				if(rowKey == null){
					continue;
				}
				String key = getCellStringValue(rowKey,0).replaceAll(" ", "");
				if(key.equals("#NAME")){
					String name = getCellStringValue(rowKey,1).replaceAll(" ", "");
					String desc = getCellStringValue(rowKey,2);
					insert(name,desc,hash);
				}else if(key.contains("#PARENT")){
					String parent = getCellStringValue(rowKey,1).replaceAll(" ", "");
					if(parent != null){
						getNode().parent = parent;
					}
				}
				else if(key == null || key.equals("") ||key.contains("#")){
					continue;
				}else{
					CellVO cvo = new CellVO();
					getNode().cells.add(cvo);
					cvo.key = key;
					cvo.type = getCellStringValue(rowKey,1).replaceAll(" ", "");
					cvo.desc = getCellStringValue(rowKey,2);
				} 
			}
		}
		logger.info("protocol-data import success...");
	}

	
}
