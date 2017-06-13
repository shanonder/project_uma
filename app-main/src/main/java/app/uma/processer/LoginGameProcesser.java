package app.uma.processer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.uma.dao.entity.Role;
import app.uma.dao.repository.IRoleRepository;
import app.uma.dao.repository.IUserRepository;
import app.uma.model.PackModel;
import app.uma.model.RoleModel;
import app.uma.model.UserModel;
import app.uma.model.WorldModel;
import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.interfaces.INotAuthProcessor;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.request.LoginRequest;
import app.uma.net.socket.response.LoginResponse;
import app.uma.net.socket.sessions.GameSession;
import app.uma.vo.UserVO;

public class LoginGameProcesser extends MsgProcessor implements INotAuthProcessor{

	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IRoleRepository roleRepository;
	
	@Autowired
	UserModel userModel;
	
	@Autowired
	WorldModel worldModel;
	
	@Autowired
	RoleModel roleModel;
	
	@Autowired
	PackModel PackModel;
	@Override
	public void process(GameSession gameSession, ClientRequest cr) throws Exception {
		LoginRequest request = new LoginRequest(cr);
		boolean access = checkToken(request);
		if(!access){
			gameSession.close();
			return;
		}
		UserVO user = userModel.find(request.getUid());
		if(user != null){
			gameSession.setLogin(true);
			gameSession.setUser(user);
			String uid = user.db.getId();
			List<Role> roleList = roleRepository.getRoleList(uid);
			if(roleList == null || roleList.size() == 0){
				gameSession.sendMsg(new LoginResponse(201, uid, request.getToken(),request.getPlatform()));
			}
			else{
				gameSession.setRole(roleList.get(0));
				gameSession.sendMsg(new LoginResponse(200, uid, request.getToken(),request.getPlatform()));
			}
		}else{
			userModel.init(request.getUid(),request.getPlatform());
		}
	}
	private boolean checkToken(LoginRequest request) {
		return true;
	}

}
