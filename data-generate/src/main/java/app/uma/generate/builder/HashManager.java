package app.uma.generate.builder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.generate.properties.AssetsProperties;

@Component
public class HashManager {
	
	private HashMap<String,String> map;
	
	public HashManager(){
		map = new HashMap<>();
	}

	@Autowired
	private AssetsProperties assetsProps;
	
	@SuppressWarnings("unchecked")
	public void init() throws Exception {
		String path = assetsProps.getRoot() + "protocol/md5.xml";
		File file = new File(path);
		if(file.exists()){
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);   
			Element root = doc.getRootElement();
			List<Element> items = root.elements("item");
			for(Element item : items){
				map.put(item.attributeValue("name"),item.attributeValue("hash"));
			}
//			FileReader fReader  = new FileReader(file);
//			CSVReader csvReader = new CSVReader(fReader );
//			String[] str = csvReader.readNext();
//			 while (str != null) {
//		            map.put(str[0],str[1]);
//		            str = csvReader.readNext();
//		        } 
//			csvReader.close();
		}
	}
	
	public String get(String fileName){
		return map.get(fileName.toLowerCase());
	}
	
	public void update(String fileName,String hash){
		map.put(fileName, hash);
	}
	
	public void flush() throws Exception{
		Element root = DocumentHelper.createElement("root");
		Document document = DocumentHelper.createDocument(root); 
		for (Map.Entry<String, String> entry : map.entrySet()) { 
			String key = entry.getKey();
			String value = entry.getValue();
			Element item = root.addElement("item");
			item.addAttribute("name", key).addAttribute("hash", value);
		}  
		OutputFormat format = new OutputFormat("    ",true);  
        format.setEncoding("UTF-8");//设置编码格式  
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("md5.xml"),format);  
        xmlWriter.write(document);  
        xmlWriter.close();  
	}

//	public String getVersion() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
