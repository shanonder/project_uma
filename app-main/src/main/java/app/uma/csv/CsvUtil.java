package app.uma.csv;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import app.uma.config.AppDirProperties;
import app.uma.utils.StringUtil;
import au.com.bytecode.opencsv.CSVReader;

public class CsvUtil {


//private static final Logger log = LoggerFactory.getLogger(CsvUtil.class);
public HashMap<String, ArrayList<?>> datas;
	public CsvUtil() {
			datas = new HashMap<>();
	}
	
	@Autowired
	private AppDirProperties appDirProperties;
	
	@SuppressWarnings("unchecked")
	public <E> ArrayList<E> getCsv(String name , Class<E> cls) throws  Exception{
		String key = null;
		if(name.contains(".dat")){
			key = name;
		}else{
			key = name + ".dat";
		}
		ArrayList<E> list = (ArrayList<E>) datas.get(key);
		if(datas.get(key) != null){
			return list;
		}
		list = new ArrayList<>();
		File file = new File(appDirProperties.getCsv() + key );

		FileReader fReader = new FileReader(file);
		@SuppressWarnings("resource")
		CSVReader csvReader = new CSVReader(fReader); 
		String[] props = csvReader.readNext();
		ArrayList<String> functions = new ArrayList<>();
		for(String str : props){
			if(str == null || str == ""){
				functions.add(null);
			}else{
				functions.add("set" + StringUtil.UpperCaseFirst(str));
			}
		}
		String[] types = csvReader.readNext();
		@SuppressWarnings("unused")
		String[] keys = csvReader.readNext();
		String[] strs = null;
		while((strs = csvReader.readNext()) != null){
			if(strs != null && strs.length > 0){
				Object data = cls.newInstance();
				for(int i = 0 ; i < functions.size(); ++i){
					String func = functions.get(i);
					String type = types[i];
					if(type.equalsIgnoreCase("int")){
						Method mtd = cls.getMethod(func,int.class);
						mtd.invoke(data,Integer.parseInt(strs[i]));
					}
					if(type.equalsIgnoreCase("string")){
						Method mtd = cls.getMethod(func,String.class);
						mtd.invoke(data,strs[i]);
					}
					if(type.equalsIgnoreCase("boolean")){
						Method mtd = cls.getMethod(func,Boolean.class);
						boolean b;
						if(strs[i] == null || strs[i] == "0" || strs[i] == ""){
							b = false;
						}else{
							b = true;
						}
						mtd.invoke(data,b);
					}
				}
				list.add((E) data);
			}  
		}
		return list; 

	}

}
