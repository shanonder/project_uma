package app.uma.modules.scene;

import app.uma.enums.SceneEntityEnum;

public interface ISceneEntity {
	
	public String getInsId();
	public SceneEntityEnum getEType();
	
	public int getSceneId();
	public double getPosX();
	public double getPosY();
}
