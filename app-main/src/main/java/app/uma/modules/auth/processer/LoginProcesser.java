package app.uma.modules.auth.processer;

import app.uma.Application;
import app.uma.model.RoleModel;
import app.uma.model.UserModel;
import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.interfaces.INotAuthProcessor;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.request.LoginRequest;
import app.uma.net.socket.response.LoginResponse;
import app.uma.net.socket.sessions.GameSession;
import app.uma.vo.RoleVO;
import app.uma.vo.UserVO;

public class LoginProcesser extends MsgProcessor implements INotAuthProcessor{


	@Override
	public void process(GameSession gameSession, ClientRequest cr) throws Exception {
		LoginRequest request = new LoginRequest(cr);
		boolean access = checkToken(request);
		if(!access){
			gameSession.close();
			return;
		}
		UserModel userModel = Application.context.getBean(UserModel.class);
		RoleModel roleModel = Application.context.getBean(RoleModel.class);
		String uid = request.getUid();
		UserVO user = userModel.find(uid);
		if(user != null){
			gameSession.setLogin(true);
			gameSession.setUser(user);
			RoleVO role = roleModel.getRoleByUid(uid, gameSession);
			if(role == null){
				gameSession.sendMsg(new LoginResponse(201, uid, request.getToken(),request.getPlatId()));
			}
			else{
				gameSession.setRole(role);
				gameSession.sendMsg(new LoginResponse(200, uid, request.getToken(),request.getPlatId()));
			}
		}else{
			userModel.init(request.getUid(),request.getPlatId());
		}
	}
	private boolean checkToken(LoginRequest request) {
		return true;
	}

}
