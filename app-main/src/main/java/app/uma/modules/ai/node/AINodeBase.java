package app.uma.modules.ai.node;

import app.uma.modules.ai.interfaces.IAIEntity;
import app.uma.modules.ai.interfaces.IAINode;

public abstract class AINodeBase implements IAINode {

private IAIEntity target;
	
	public AINodeBase(IAIEntity target) {
		this.target = target;
	}

	@Override
	public IAIEntity getTarget() {
		return target;
	}

}
