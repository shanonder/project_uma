package app.uma.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.dao.entity.Role;
import app.uma.dao.repository.IRoleRepository;
import app.uma.modules.role.create.RoleCreateProcesser;
import app.uma.net.socket.consts.ProtocolConst;
import app.uma.net.socket.request.RoleCreateRequest;
import app.uma.net.socket.response.RoleCreateResponse;
import app.uma.net.socket.sessions.GameSession;
import app.uma.utils.StringUtil;
import app.uma.vo.RoleVO;
import app.uma.vo.UserVO;

@Component
public class RoleModel extends ModelBase {

	@Autowired
	private IRoleRepository roleRepository;
	
	
	public RoleVO getRoleByUid(String uid , GameSession session) {
		Role role = roleRepository.getRoleByUid(uid);
		if(role == null){
			return null;
		}
		return new RoleVO(session, role);
	}
	
	public int create(RoleCreateRequest request, GameSession gameSession) throws Exception {
		String name = request.getName();
		int state = 200;
		if(name.getBytes().length < 6){
			state = 202;
			gameSession.sendMsg(new RoleCreateResponse(state));
			return state;
		}
		if(StringUtil.shieldedWordCheck(name) == false){
			state = 203;
			gameSession.sendMsg(new RoleCreateResponse(203));
			return state;
		}
		if(roleRepository.findOneByRoleName(request.getName()) != null){
			state = 201;
			gameSession.sendMsg(new RoleCreateResponse(201));
			return state;
		}
		UserVO user = gameSession.getUser(UserVO.class);
		Role role = new Role();
		role.setUid(user.db.getId());
		role.setName(name);
		role.setProfession(request.getProfId());
		role.setLevel(1);
		role.setExp(0);
		roleRepository.save(role);
		gameSession.setRole(getRoleByUid(user.db.getId(), gameSession));
		gameSession.sendMsg(new RoleCreateResponse(state));
		return state;
	}

	@Override
	public void registProsesser() {
		registProcess(ProtocolConst.RoleCreateRequest , RoleCreateProcesser.class);
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		
	}
}
