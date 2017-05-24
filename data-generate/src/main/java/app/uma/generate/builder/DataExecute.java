package app.uma.generate.builder;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.context.ApplicationContext;

import app.uma.generate.SpringContextUtil;
import app.uma.generate.properties.AssetsProperties;
import app.uma.generate.properties.Config;
import app.uma.generate.vo.CellVO;
import app.uma.generate.vo.DataOptCell;
import app.uma.utils.MD5Util;



public class DataExecute {
	private static final Logger logger = Logger.getLogger(DataExecute.class);
	
//	private static AsRegisterBuilder asRegisterBuilder;
//	static {
//		asRegisterBuilder = new AsRegisterBuilder("DataRegister", null, Version.version);
//	}
	private DataOptCell optCell;
	
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
			return null;
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue();
	}
	public void execute() throws Exception{
		ApplicationContext context = SpringContextUtil.getApplicationContext();
		GenerateVersion version = context.getBean(GenerateVersion.class);
		Config config = context.getBean(Config.class);
		AssetsProperties assetsProp = (AssetsProperties)context.getBean(AssetsProperties.class);
		String fileName = "data.xlsx";
		File file = new File(assetsProp.getProtocolXlsxPath() + fileName);
		String hash = MD5Util.getMd5ByFile(file);
		String oVer = version.get(fileName);
		if(config.isGenerateAll() == false && oVer.equals(hash)){
			logger.info("File No Change:" + fileName);
			return;
		}
		version.update(fileName, hash);
		Workbook book =  WorkbookFactory.create(file);
		
		int size = book.getNumberOfSheets();
		for(int i = 0 ; i < size ; ++i){
//			Config.counter.set(i*1000);
			Sheet sheet = book.getSheetAt(i);
			int length = sheet.getLastRowNum() + 1;
			for(int j = 0 ; j < length ; ++j){
				Row rowKey = sheet.getRow(j);
				if(rowKey == null){
					continue;
				}
				String key = getCellStringValue(rowKey,0).replaceAll(" ", "");
				if(key.equals("#NAME")){
					operateCell();
					optCell = new DataOptCell();
					optCell.md5 = hash;
					optCell.name = getCellStringValue(rowKey,1).replaceAll(" ", "");
					optCell.desc = getCellStringValue(rowKey,2);
				}else if(key.contains("#PARENT")){
					String parent = getCellStringValue(rowKey,1).replaceAll(" ", "");
					if(parent != null){
						optCell.parent = parent;
					}
				}
				else if(key == null || key.equals("") ||key.contains("#")){
					continue;
				}else{
					CellVO cvo = new CellVO();
					optCell.cells.add(cvo);
					cvo.key = key;
					cvo.type = getCellStringValue(rowKey,1).replaceAll(" ", "");
					cvo.desc = getCellStringValue(rowKey,2);
				} 
			}
		}
		operateCell();
		asRegisterBuilder.frush();
	}

	private void operateCell() {
		if(optCell != null){
			optCell.operate();
			asRegisterBuilder.addData(optCell.name);
			optCell = null;
		}		
	}
}
