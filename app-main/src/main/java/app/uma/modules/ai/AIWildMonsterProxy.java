package app.uma.modules.ai;

import app.uma.modules.ai.interfaces.IAIEntity;

public class AIWildMonsterProxy extends AIProxyBase {

	private IAIEntity aiEntity;
	public AIWildMonsterProxy(IAIEntity aiEntity) {
		this.aiEntity = aiEntity;
		
	}
	
	@Override
	public void doAction() {

	}

	@Override
	public void awake() {

	}

	@Override
	public void sleep() {

	}

	@Override
	public IAIEntity getTarget() {
		return aiEntity;
	}

}
