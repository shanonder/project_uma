package app.uma.generate.builder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import app.uma.generate.properties.AssetsProperties;

@Component
public class ExcelVersionUtil {
	
	@Autowired
	private AssetsProperties assetsProperties;
	
	private HashMap<String,String> map;
	public ExcelVersionUtil() {
		map = new HashMap<>();
	}
	public void init() throws IOException {
		File file = new File(assetsProperties.getExcelVersionUrl());
		if(!file.exists()){
		}else{
			FileReader fReader  = new FileReader(file);
			CSVReader csvReader = new CSVReader(fReader );
			String[] str = csvReader.readNext();
			 while (str != null) {
		            map.put(str[0],str[1]);
		            str = csvReader.readNext();
		        } 
			List<String[]> list = csvReader.readAll();
			for(String[] ss : list){  
				for(String s : ss)  
					if(null != s && !s.equals(""))  
						System.out.print(s + " , ");  
				System.out.println();  
			}  
			csvReader.close();
		}
	}
	
	public String get(String fileName){
		return map.get(fileName.toLowerCase());
	}
	
	public void update(String fileName,String hash){
		map.put(fileName, hash);
	}
	
	public void flush() throws Exception{
		File file = new File(assetsProperties.getExcelVersionUrl());
		Writer writer = new FileWriter(file); 
		CSVWriter csvw = new CSVWriter(writer);
		for (Map.Entry<String, String> entry : map.entrySet()) { 
			String key = entry.getKey();
			String value = entry.getValue();
		    String[] nextLine = new String[2];
		    nextLine[0] = key;
		    nextLine[1] = value;
		    csvw.writeNext(nextLine);
		}
		csvw.flush();
		csvw.close();
	}
}
