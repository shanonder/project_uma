package app.uma.csv;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import app.uma.utils.StringUtil;
import au.com.bytecode.opencsv.CSVReader;

@Component
public class CsvUtil {
	static {
		datas = new HashMap<>();
	}
	public static HashMap<String, ArrayList<?>> datas;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList<?> getCsv(String name , Class<?> cls) throws  Exception{
		String key = null;
		if(name.contains(".dat")){
			key = name;
		}else{
			key = name + ".dat";
		}
		ArrayList list = datas.get(key);
		if(datas.get(key) != null){
			return list;
		}
		list = new ArrayList<>();
		File file = new File("C:/workspaces/wp_spring/project_uma/app-main/assets/csvs/" + key );

		FileReader fReader = new FileReader(file);
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
				list.add(data);
			}  
		}
		return list; 

	}

}
