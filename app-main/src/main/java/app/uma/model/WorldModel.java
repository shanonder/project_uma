package app.uma.model;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import app.uma.dao.entity.Role;
import app.uma.net.socket.sessions.GameSession;
import app.uma.vo.SceneVO;

@Component
public class WorldModel extends ModelBase {
	private HashMap<Integer, SceneVO> scene;
	public void add(Role role){
		role.getSceneId();
	}

	public void enterWorld(GameSession gameSession) {
//		scene
	}


	@Override
	public void registProsesser() {
		
	}

	@Override
	public void startup() {
		
	}
}
