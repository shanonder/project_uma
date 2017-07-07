package app.uma.modules.pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.base.Ifactory;
import app.uma.csv.CsvUtil;
import app.uma.dao.entity.Pack;
import app.uma.database.PackCfg;
import app.uma.modules.item.ItemFactory;
import app.uma.vo.ItemVO;
import app.uma.vo.PackVO;
import net.sf.json.JSONObject;

@Component
public class PackFactory implements Ifactory {

	@Autowired
	private CsvUtil csvUtil;
	private HashMap<Integer,PackCfg> dtPackMap;
	private ItemFactory itemFactory;
	@Override
	public void initCfgs() {
		setDtPackMap(new HashMap<>());
		try {
			ArrayList<PackCfg> list;
			list = csvUtil.getCsv("pack.dat",PackCfg.class);
			for (PackCfg dt : list){
				getDtPackMap().put(dt.getType(), dt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public PackVO initPack(Pack db , PackCfg cfg) throws Exception{
		PackVO vo = new PackVO(db , cfg);
		String contentStr = vo.getDb().getContent();
		if(contentStr != null){
			JSONObject itemMap = JSONObject.fromObject(contentStr);
			Iterator<?> itera = itemMap.keys();  
			while(itera.hasNext()){
				String key = (String)itera.next();
				JSONObject value = itemMap.getJSONObject(key);
				ItemVO itemVO =itemFactory.getItemByJson(value);
				vo.setItem(Integer.parseInt(key), itemVO);
			}
		}
		return vo;
	}
	public HashMap<Integer,PackCfg> getDtPackMap() {
		return dtPackMap;
	}
	public void setDtPackMap(HashMap<Integer,PackCfg> dtPackMap) {
		this.dtPackMap = dtPackMap;
	}

}
