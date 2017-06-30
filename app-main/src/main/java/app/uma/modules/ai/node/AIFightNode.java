package app.uma.modules.ai.node;

import app.uma.modules.ai.interfaces.IAIEntity;
import app.uma.modules.ai.interfaces.IAINode;

public class AIFightNode extends AINodeBase implements IAINode {

	public AIFightNode(IAIEntity target) {
		super(target);
	}

	@Override
	public boolean check() {
		return false;
	}
}
