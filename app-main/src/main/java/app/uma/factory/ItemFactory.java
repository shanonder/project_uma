package app.uma.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.csv.CsvUtil;
import app.uma.database.ItemCfg;
import app.uma.enums.ItemTypeEnum;
import app.uma.vo.EquipVO;
import app.uma.vo.ItemVO;
import net.sf.json.JSONObject;

@Component
public class ItemFactory implements Ifactory{
	
	@Autowired
	private CsvUtil csvUtil;
	
	private HashMap<Integer,ItemCfg> itemMap;
	public ItemCfg getCfg(int cfgId){
		return itemMap.get(cfgId);
	}

	

	public ItemVO getItemByJson(JSONObject value) {
		int type = value.getInt("type");
		ItemCfg dtItem = getCfg(value.getInt("cfgId"));
		ItemVO itemVO;
		if(type == ItemTypeEnum.EQUIP.getType()){
			itemVO = new EquipVO(dtItem);
		}
		else{
			itemVO = new ItemVO(dtItem);
		}
		return itemVO;
	}

	public void initCfgs() {
		itemMap = new HashMap<>();
		ArrayList<ItemCfg> items;
		try {
			items = csvUtil.getCsv("item.dat",ItemCfg.class);
			for(ItemCfg item : items ){
				itemMap.put(item.getId(), item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
