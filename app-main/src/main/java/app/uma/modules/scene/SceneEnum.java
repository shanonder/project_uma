package app.uma.modules.scene;

public enum SceneEnum {
	WILD(1),DUNGEON(2);
	private int type;
	private SceneEnum(int type) {
		this.setType(type);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
