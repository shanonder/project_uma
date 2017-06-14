package app.uma.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.dao.entity.Role;
import app.uma.dao.repository.IRoleRepository;
import app.uma.net.socket.request.RoleCreateRequest;
import app.uma.net.socket.response.RoleCreateResponse;
import app.uma.net.socket.sessions.GameSession;
import app.uma.utils.StringUtil;
import app.uma.vo.RoleVO;

@Component
public class RoleModel {

	@Autowired
	private IRoleRepository roleRepository;
	public RoleVO getRoleByUid(String uid , GameSession session) {
		Role role = roleRepository.getRoleByUid(uid);
		if(role == null){
			return null;
		}
		return new RoleVO(session, role);
	}
	
	public void create(RoleCreateRequest request, GameSession gameSession) throws Exception {
		String name = request.getName();
		if(name.length() < 6){
			gameSession.sendMsg(new RoleCreateResponse(202));
			return;
		}
		if(StringUtil.shieldedWordCheck(name) == false){
			gameSession.sendMsg(new RoleCreateResponse(203));
			return;
		}
		if(roleRepository.hasRole(request.getName()) != null){
			gameSession.sendMsg(new RoleCreateResponse(201));
			return;
		}
		Role role = new Role();
		role.setName(name);
		role.setProfession(request.getProfId());
		role.setLevel(1);
		role.setExp(0);
		roleRepository.save(role);
		gameSession.sendMsg(new RoleCreateResponse(200));
	}

}
