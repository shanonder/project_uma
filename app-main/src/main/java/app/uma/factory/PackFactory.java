package app.uma.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.csv.CsvUtil;
import app.uma.dao.entity.Pack;
import app.uma.database.DtPack;
import app.uma.vo.ItemVO;
import app.uma.vo.PackVO;
import net.sf.json.JSONObject;

@Component
public class PackFactory implements Ifactory {

	@Autowired
	private CsvUtil csvUtil;
	private HashMap<Integer,DtPack> dtPackMap;
	private ItemFactory itemFactory;
	@Override
	public void initCfgs() {
		setDtPackMap(new HashMap<>());
		try {
			ArrayList<DtPack> list;
			list = csvUtil.getCsv("pack.dat",DtPack.class);
			for (DtPack dt : list){
				getDtPackMap().put(dt.getType(), dt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public PackVO initPack(Pack db , DtPack cfg) throws Exception{
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
	public HashMap<Integer,DtPack> getDtPackMap() {
		return dtPackMap;
	}
	public void setDtPackMap(HashMap<Integer,DtPack> dtPackMap) {
		this.dtPackMap = dtPackMap;
	}

}
