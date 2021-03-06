package app.uma.modules.pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonObject;

import app.uma.dao.entity.Pack;
import app.uma.database.PackCfg;
import app.uma.modules.item.ItemVO;
import app.uma.net.socket.data.GridData;
import app.uma.net.socket.data.PackData;

public class PackEntity {
	private Pack db;
	private PackCfg cfg;

	private HashMap<Integer, ItemVO> itemdataMap;

	public PackEntity() {
		itemdataMap = new HashMap<>();
	}
	
	public PackEntity(Pack db , PackCfg cfg) {
		this.db = db;
		this.setCfg(cfg);
		itemdataMap = new HashMap<>();
	}

	public Pack getDb() {
		return db;
	}

	public void setItem(int index, ItemVO item){
		if(item == null){
			itemdataMap.remove(index);
		}else{
			itemdataMap.put(index, item);
		}
	}


	public PackData toMsg(){
		PackData packData = new PackData();
		packData.setOpenLength(db.getOpenLenth());
		packData.setType(db.getType());
		ArrayList<GridData> gridDatas = new ArrayList<>();
		Iterator<Entry<Integer, ItemVO>> iter = itemdataMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Integer, ItemVO> entry = iter.next();
			int index = entry.getKey();
			ItemVO item = entry.getValue();
			GridData gridData = new GridData();
			gridData.setIndex(index);
			gridData.setItem(item.toMsg());
		}
		packData.setItemList(gridDatas);
		
		return packData;
	}
	
	public void updateDataContent(){
		Iterator<Entry<Integer, ItemVO>> iter = itemdataMap.entrySet().iterator();
		JsonObject json = new JsonObject();
		while (iter.hasNext()) {			
		}
		
		db.setContent(json.toString());
	}
	
	public void save(){
		
	}

	public PackCfg getCfg() {
		return cfg;
	}

	public void setCfg(PackCfg cfg) {
		this.cfg = cfg;
	}

}
