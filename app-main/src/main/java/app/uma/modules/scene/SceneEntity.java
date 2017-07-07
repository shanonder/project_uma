package app.uma.modules.scene;

import java.util.HashMap;

import app.uma.database.SceneCfg;

public abstract class SceneEntity {
	private SceneCfg cfg;
	private HashMap<String, ISceneEntity> hashMap;
	public SceneEntity() {
		hashMap = new HashMap<>();
	}
	public SceneCfg getCfg() {
		return cfg;
	}
	public void setCfg(SceneCfg cfg) {
		this.cfg = cfg;
	}
	
	public abstract void run();
	
	public abstract void stop();
	
	
	public void addEntity(ISceneEntity entity) {
		hashMap.put(entity.getInsId(), entity);
	}
	public void removeEntity(String insId) {
		hashMap.remove(insId);
	}
	
}
