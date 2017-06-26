package app.uma.vo;

import app.uma.database.ItemCfg;
import app.uma.net.socket.data.ItemData;

public class ItemVO {
//	private Item db;
	private ItemCfg cfg;
	
	public ItemVO(ItemCfg cfg) {
//		this.db = db;
		this.cfg = cfg;
	}
	
	public int getType(){
		return cfg.getType();
	}
	
	private String insId;
	private int cfgId;
	private long createTime;
	
	
	public ItemData toMsg() {
		ItemData itemData = new ItemData();
		itemData.setInsId(insId);
		itemData.setCfgId(cfgId);
		itemData.setCreateTime(createTime);
		return itemData;
	}

	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

	public int getCfgId() {
		return cfgId;
	}

	public void setCfgId(int cfgId) {
		this.cfgId = cfgId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
//	@Override
//	public String toString(){
//		
//		
//		
//	}
}
