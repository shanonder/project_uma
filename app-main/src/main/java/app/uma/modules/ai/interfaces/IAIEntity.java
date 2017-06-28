package app.uma.modules.ai.interfaces;

import app.uma.modules.ai.interfaces.IAIProxy;

public interface IAIEntity {

//	/**
//	 * 
//	 * @return 阵营 0中立 -1 怪物 -2NPC
//	 */
//	public int relation(IAIEntity target);

	public void setAIProxy(IAIProxy iaiProxy);

	public IAIProxy getAIProxy();
}
