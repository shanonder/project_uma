package app.uma.generate.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;

import app.uma.generate.builder.as.AsExcelBuilder;
import app.uma.generate.builder.java.JavaExcelBuilder;
import app.uma.generate.node.CellVO;
import app.uma.generate.properties.AssetsProperties;
import app.uma.generate.properties.Config;
import app.uma.utils.CreateFileUtil;
import app.uma.utils.MD5Util;

@Component
public class ExcelBuilder {

	private static final Logger logger = LoggerFactory.getLogger(ExcelBuilder.class);
	
	@Autowired
	private AssetsProperties assetsProperties;
	@Autowired
	private ExcelVersionUtil excelVersionUtil;
	@Autowired
	private Config config;
	@Autowired
	private AsExcelBuilder asExcelBuilder;
	@Autowired
	private JavaExcelBuilder javaExcelBuilder;
	

	
	public void excute(){
		File dir = new File(assetsProperties.getExcelDataPath());
		CreateFileUtil.createDir(assetsProperties.getcXlsOutPath());
		CreateFileUtil.createDir(assetsProperties.getsXlsOutPath());
		asExcelBuilder.createDir();
		javaExcelBuilder.createDir();
		if(dir.exists() && dir.isDirectory()){
			File[] files = dir.listFiles();
			for (File file : files) {
				try {
					ExcelToCsv(file);
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("FileTransError:" + file.getName() + e.getMessage());
				}
			}
		}else{
			logger.warn("Error excelDir:" + assetsProperties.getExcelDataPath());
		}
	}
	private String getCellStringValue(Cell cell) {
		if(cell == null){
			return null;
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue(); 
	}



	private void ExcelToCsv(File file) throws Exception{
		if(file.exists() && !file.isDirectory()){
			String fileName = file.getName();
			if(fileName.contains("~$")){//临时文件
				return;
			}
			String prefix = fileName.substring(fileName.lastIndexOf(".")+1);
			if(prefix.equals("xls") || prefix.equals("xlsx")){
				String oVer = excelVersionUtil.get(fileName);
				String hash = MD5Util.getMd5ByFile(file);
				if(config.isGenerateAll() == false && oVer.equals(hash)){
//					API.log.info("File No Change:" + fileName);
					return;
				}
				excelVersionUtil.update(fileName, hash);
				Workbook book = null;
				book = WorkbookFactory.create(file);
				Sheet sheet = book.getSheetAt(0);
				int lastRowNum = sheet.getLastRowNum();
				if(lastRowNum < 4){
//					API.log.warn("FileError:" + file.getName() + ";row count:" + lastRowNum);
				}
				Row rowPropName = sheet.getRow(0);
				int lastCellNum = rowPropName.getLastCellNum();
				Row rowType = sheet.getRow(1);
				Row rowCS = sheet.getRow(2);
				Row rowDesc = sheet.getRow(3);
				ArrayList<CellVO> cellVOS = new ArrayList<CellVO>();
				CSVWriter ccsvw = null;
				CSVWriter scsvw = null;
				ArrayList<String> cks = new ArrayList<>();
				ArrayList<String> sks = new ArrayList<>();
				for(int j = 0 ; j < lastCellNum; ++j){
					Cell cellKey = rowPropName.getCell(j);
					String key = getCellStringValue(cellKey);
					if(key != null && key.replaceAll(" ", "").equals("") == false){
						CellVO cellVO = new CellVO();
						cellVO.cs = rowCS.getCell(j).getStringCellValue();
						cellVO.type = rowType.getCell(j).getStringCellValue();
						cellVO.desc = getCellStringValue(rowDesc.getCell(j));
						cellVO.index = j;
						cellVO.key = key;
						cellVOS.add(cellVO);

						if(cellVO.cs.indexOf("C") > -1){
							cks.add(cellVO.key);
						}
						if(cellVO.cs.indexOf("S") > -1){
							sks.add(cellVO.key);
						}
					}
				}
				int cSize = cks.size();
				int sSize = sks.size();
				String name = fileName.substring(0,fileName.lastIndexOf("."));
				String fName = name + ".dat";
				if(cSize > 0){
					String cpath = assetsProperties.getcXlsOutPath() + fName;
					File cfile = new File(cpath);					
					Writer cwriter = new FileWriter(cfile); 
					ccsvw = new CSVWriter(cwriter,CSVWriter.DEFAULT_SEPARATOR,CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER);
					asExcelBuilder.frush(name, cellVOS, hash);
				}
				if(sSize > 0){
					String spath = assetsProperties.getsXlsOutPath() + fName;
					File sfile = new File(spath);
					Writer swriter = new FileWriter(sfile); 
					scsvw = new CSVWriter(swriter);
					javaExcelBuilder.frush(name, cellVOS , hash);
				}
				
				Row row = null;
				ArrayList<String> cv = null;
				ArrayList<String> sv = null;
				for(int i = 0 ; i <= lastRowNum ; i++){
					if(i ==  2|| i == 3){
						continue;
					}
					if(cSize > 0){
						cv = new ArrayList<String>();
					}
					if(sSize > 0){
						sv = new ArrayList<String>();
					}
					row = sheet.getRow(i);
					if(row == null){
						if(i != 4){
							continue;
						}
					}
					String v = null;
					for(CellVO cellVO : cellVOS){
						if(row == null){
							v = "";
						} else{
							v = getCellStringValue(row.getCell(cellVO.index));
						}
						if(cellVO.cs.indexOf("C") != -1){
							cv.add(v);
						}
						if(cellVO.cs.indexOf("S") != -1){
							sv.add(v);
						}
					}
					if(cSize > 0){
						ccsvw.writeNext(cv.toArray(new String[0]));
					}
					if(sSize > 0){
						scsvw.writeNext(sv.toArray(new String[0]));
					}
				}
				if(cSize>0){
					ccsvw.flush();
					ccsvw.close();
				}
				if(sSize>0){
					scsvw.flush();
					scsvw.close();
				}
			}
//			API.log.info("File Opt OK:" + file.getName());
		}
	}
//	todo
	public void dispose() {
		
	}
}
