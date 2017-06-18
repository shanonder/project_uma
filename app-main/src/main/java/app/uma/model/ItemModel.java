package app.uma.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.csv.CsvUtil;
import app.uma.dao.entity.Item;
import app.uma.dao.repository.IItemRepository;
import app.uma.database.DtItem;
import app.uma.vo.ItemVO;

@Component
public class ItemModel extends ModelBase{
	
	@Autowired
	private IItemRepository itemRepository;
	
	@Autowired
	private CsvUtil csvUtil;
	
	private HashMap<Integer,DtItem> itemMap;

	
	public DtItem getCfg(int cfgId){
		return itemMap.get(cfgId);
	}

	public ItemVO genItemByInsId(String insId) {
		Item item = itemRepository.findOne(insId);
		DtItem dtItem = getCfg(item.getSourceId());
		ItemVO itemVO;
		int type = dtItem.getType();
		
//		if(type == ItemTypeEnum.EQUIP.getType()){
			itemVO = new ItemVO(item, dtItem);
//		}
		itemVO = new ItemVO(item, dtItem);
		return itemVO;
	}

	public ItemVO getItem(Object value) {
//		int type = value.
		return null;
	}

	@Override
	protected void initCfg(){
		itemMap = new HashMap<>();
		ArrayList<DtItem> items;
		try {
			items = csvUtil.getCsv("item.dat",DtItem.class);
			for(DtItem item : items ){
				itemMap.put(item.getId(), item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void registProsesser() {
		// TODO Auto-generated method stub
		
	}
}
