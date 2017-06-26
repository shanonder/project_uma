package app.uma.factory;

import java.util.Iterator;

import org.springframework.stereotype.Component;

import app.uma.dao.entity.Pack;
import app.uma.database.DtPack;
import app.uma.vo.ItemVO;
import app.uma.vo.PackVO;
import net.sf.json.JSONObject;

@Component
public class PackFactory implements Ifactory {

	private ItemFactory itemFactory;
	@Override
	public void initCfgs() {
		
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

}
