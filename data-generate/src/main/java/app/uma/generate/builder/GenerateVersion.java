package app.uma.generate.builder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import app.uma.generate.properties.AssetsProperties;

@Component
public class GenerateVersion {
	
	private HashMap<String,String> map;
	
	public GenerateVersion(){
		map = new HashMap<>();
	}

	@Autowired
	private AssetsProperties assetsProps;
	
	private String path;
	public void init() throws IOException {
		path = assetsProps.getRoot() + "protocol/md5.csv";
		File file = new File(path);
		if(file.exists()){
			FileReader fReader  = new FileReader(file);
			CSVReader csvReader = new CSVReader(fReader );
			String[] str = csvReader.readNext();
			 while (str != null) {
		            map.put(str[0],str[1]);
		            str = csvReader.readNext();
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
		File file = new File(path);
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
