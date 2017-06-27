package app.uma.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.uma.Application;
import app.uma.factory.MonsterFactory;
import app.uma.factory.SceneFactory;

public class WildSceneEntity extends SceneEntity {
	
	private static final Logger log = LoggerFactory.getLogger(WildSceneEntity.class);

	@Override
	public void run() {
		SceneFactory sceneFactory = Application.context.getBean(SceneFactory.class);
		MonsterFactory monsterFactory = Application.context.getBean(MonsterFactory.class);
		addEntity(monsterFactory.initMonster(1,3,5));
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
