package app.uma.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class CsvUtll {
	public static String getCellStringValue(Row row,int index) {
		Cell cell = row.getCell(index);
		if(cell == null){
			return null;
		}
		cell.setCellType(CellType.STRING);
		return cell.getStringCellValue();
	}
	
	
	public static String upperFirestChar(String src) {
		return src.substring(0, 1).toUpperCase().concat(src.substring(1));
	}
}
