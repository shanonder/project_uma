package app.uma.modules.scene;

public enum SceneEntityEnum {
	ROLE(1),MONSTER(2),NPC(3),ITEM(4),MONEY(5),BUFF(6);
	private int type;
	private SceneEntityEnum(int type) {
		this.setType(type);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
