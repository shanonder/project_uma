package app.uma.modules.task;

public enum TaskTypeEnum {
	KILL(1),COLLECT(2),TALK(3),TIME_LIMIT(4);
	
	private int type;
	private TaskTypeEnum(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
}
