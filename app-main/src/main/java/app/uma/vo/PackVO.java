package app.uma.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import app.uma.Application;
import app.uma.dao.entity.Pack;
import app.uma.database.DtPack;
import app.uma.model.ItemModel;
import app.uma.net.socket.data.GridData;
import app.uma.net.socket.data.PackData;

public class PackVO {
	private Pack db;
	private DtPack cfg;

	private HashMap<Integer, ItemVO> itemdataMap;

	public PackVO() {
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
		String content = "";
		Iterator<Entry<Integer, ItemVO>> iter = itemdataMap.entrySet().iterator();
		while (iter.hasNext()) {
			if(content.equals("") == false){
				content += "-";
			}
			Entry<Integer, ItemVO> entry = iter.next();
			int index = entry.getKey();
			ItemVO item = entry.getValue();
			GridData gridData = new GridData();
			content += Integer.toString(index) + ";" + item.getDb().getId();
		}
		db.setContent(content);
	}
	
	


	public static PackVO init(Pack db , DtPack cfg) throws Exception{
		PackVO vo = new PackVO();
		vo.db = db;
		vo.cfg = cfg;
		String contentStr = vo.getDb().getContent();
		if(contentStr != null){
			JSONObject jsonObject = new JSONObject();
			Iterator itera = jsonObject.keys();  
			while(itera.hasNext()){
				String key = (String)itera.next();
				Object value =jsonObject.getString(key);
				ItemModel itemModel = Application.context.getBean(ItemModel.class);
				ItemVO itemVO =itemModel.getItem(value);
			}
//			String[] items = contentStr.split(";");
//			for(String ele : items){
//				String[] e = ele.split("-");
//				ItemModel itemModel = Application.context.getBean(ItemModel.class);
//				ItemVO itemVO = itemModel.genItemByInsId(e[1]);
//				
//				vo.setItem(Integer.parseInt(e[0]), itemVO);
//			}
		}
		return vo;
	}



}
