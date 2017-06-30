package app.uma.modules.ai.node;

import app.uma.modules.ai.interfaces.IAIEntity;
import app.uma.modules.ai.interfaces.IAINode;

public class AIPatrolNode extends AINodeBase implements IAINode{

	/**
	 * 巡逻
	 * @param target
	 */
	public AIPatrolNode(IAIEntity target) {
		super(target);
	}

	@Override
	public boolean check() {
		
		return false;
	}
}
