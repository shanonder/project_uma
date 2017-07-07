package app.uma.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.uma.Application;
import app.uma.modules.ai.AIWildMonsterProxy;
import app.uma.modules.npc.MonsterFactory;
import app.uma.modules.scene.SceneFactory;

public class WildSceneEntity extends SceneEntity {
	
	private static final Logger log = LoggerFactory.getLogger(WildSceneEntity.class);

	@Override
	public void run() {
		SceneFactory sceneFactory = Application.context.getBean(SceneFactory.class);
		MonsterFactory monsterFactory = Application.context.getBean(MonsterFactory.class);
		MonsterEntity monsterEntity = monsterFactory.initMonster(1,3,5);
		monsterEntity.setAIProxy(new AIWildMonsterProxy(monsterEntity));
		addEntity(monsterEntity);
		monsterEntity.getAIProxy().awake();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
