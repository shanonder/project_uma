package app.uma.vo;

import app.uma.database.MonsterCfg;
import app.uma.enums.SceneEntityEnum;
import app.uma.modules.ai.interfaces.IAIEntity;
import app.uma.modules.ai.interfaces.IAIProxy;
import app.uma.modules.scene.ISceneEntity;

public class MonsterEntity implements ISceneEntity ,IAIEntity {
	
	private String insId;
	private int sceneId;
	private double posX;
	private MonsterCfg cfg;
	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	private double posY;
	@Override
	public String getInsId() {
		// TODO Auto-generated method stub
		return insId;
	}

	@Override
	public SceneEntityEnum getEType() {
		// TODO Auto-generated method stub
		return SceneEntityEnum.MONSTER;
	}

	@Override
	public int getSceneId() {
		// TODO Auto-generated method stub
		return this.sceneId;
	}

	@Override
	public double getPosX() {
		// TODO Auto-generated method stub
		return this.posX;
	}

	@Override
	public double getPosY() {
		// TODO Auto-generated method stub
		return this.posY;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

	public MonsterCfg getCfg() {
		return cfg;
	}

	public void setCfg(MonsterCfg cfg) {
		this.cfg = cfg;
	}


	private IAIProxy aiProxy;
	
	@Override
	public void setAIProxy(IAIProxy aiProxy) {
		this.aiProxy = aiProxy;
	}

	@Override
	public IAIProxy getAIProxy() {
		return aiProxy;
	}
}
