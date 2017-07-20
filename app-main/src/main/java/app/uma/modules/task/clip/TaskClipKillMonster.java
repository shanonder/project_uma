package app.uma.modules.task.clip;

public class TaskClipKillMonster extends TaskClip {

	private int monsterId;
	private int count;
	private int current;
	
	public int getMonsterId(){
		return monsterId;
	}
	public void setMonsterId(int monsterId) {
		this.monsterId = monsterId;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}

}
