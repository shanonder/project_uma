package app.uma.modules.ai.interfaces;

public interface IAIProxy {
	public IAIEntity getTarget();
	public void doAction();
	public void awake();
	public void sleep();
}
