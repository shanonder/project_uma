package app.uma.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.base.ModelBase;
import app.uma.dao.entity.Role;
import app.uma.database.SceneCfg;
import app.uma.enums.SceneEnum;
import app.uma.modules.scene.SceneFactory;
import app.uma.net.socket.sessions.GameSession;
import app.uma.vo.RoleVO;
import app.uma.vo.SceneEntity;

@Component
public class WorldModel extends ModelBase {

	@Autowired
	private SceneFactory sceneFactory;
	private HashMap<Integer, SceneEntity> hashMap;
	private HashMap<String,SceneEntity> hashMapSceneEntity;
	public void add(Role role){
		role.getSceneId();
	}

	public void enterWorld(GameSession gameSession) {
		RoleVO role = gameSession.getRole(RoleVO.class);
		if(hashMapSceneEntity.containsKey(role.getInsId())){
			SceneEntity oScene = hashMapSceneEntity.get(role.getInsId());
			oScene.removeEntity(role.getInsId());
		}
		SceneEntity scene = hashMap.get(role.getSceneId());
		scene.addEntity(role);
	}


	@Override
	public void registProsesser() {

	}

	@Override
	public void startup() {
		hashMap = new HashMap<>();
		HashMap<Integer, SceneCfg> sceneCfgs = sceneFactory.getCfg();
		Iterator<Entry<Integer, SceneCfg>> iterator = sceneCfgs.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, SceneCfg> entry =  iterator.next();
			Integer id = entry.getKey();
			SceneCfg cfg = entry.getValue();
			if(cfg.getType() == SceneEnum.WILD.getType()){
				SceneEntity entity = sceneFactory.initScene(cfg);
				hashMap.put(id, entity) ;
				entity.run();
			}
		}
	}
}
