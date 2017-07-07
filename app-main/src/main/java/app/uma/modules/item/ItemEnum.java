package app.uma.modules.item;

public enum ItemEnum {
	EQUIP(1),CONSUMABLES(2);
	
	private int type;
	private ItemEnum(int type) {
		this.setType(type);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
