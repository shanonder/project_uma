package app.uma.vo;

import app.uma.dao.entity.Item;
import app.uma.database.DtItem;
import app.uma.net.socket.data.ItemData;

public class ItemVO {
	private Item db;
	private DtItem cfg;
	
	public ItemVO(Item db ,DtItem cfg) {
		this.db = db;
		this.cfg = cfg;
	}
	public Item getDb() {
		return db;
	}
	public void setDb(Item db) {
		this.db = db;
	}
	
	public int getType(){
		return cfg.getType();
	}
	public ItemData toMsg() {
		ItemData itemData = new ItemData();
		itemData.setInsId(db.getId());
		itemData.setCfgId(db.getSourceId());
		itemData.setCreateTime(db.getCreateTime().getTime());
		return itemData;
	}
}
