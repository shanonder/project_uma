package app.uma.enums;

public enum ItemTypeEnum {
	EQUIP(1),CONSUMABLES(2);
	
	private int type;
	private ItemTypeEnum(int type) {
		this.setType(type);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
