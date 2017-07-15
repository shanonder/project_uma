package app.uma.modules.pack;

public enum PackEnum {
	BAG(0),EQUIP(1),DEPOT(2);
	
	private int type;
	private PackEnum(int type) {
		this.setType(type);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
