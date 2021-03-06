package app.uma.modules.auth.processer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.controller.EnterWorldController;
import app.uma.dao.entity.User;
import app.uma.modules.auth.UserModel;
import app.uma.modules.auth.UserVO;
import app.uma.modules.role.RoleModel;
import app.uma.modules.role.RoleVO;
import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.interfaces.INotAuthProcessor;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.request.LoginRequest;
import app.uma.net.socket.response.LoginResponse;
import app.uma.net.socket.sessions.GameSession;

@Component
public class LoginProcesser extends MsgProcessor implements INotAuthProcessor{

	@Autowired
	private UserModel userModel;
	@Autowired
	private RoleModel roleModel;
	@Autowired
	private EnterWorldController enterWorldController;
	@Override
	public void process(GameSession gameSession, ClientRequest cr) throws Exception {
		LoginRequest request = new LoginRequest(cr);
		boolean access = checkToken(request);
		if(!access){
			gameSession.close();
			return;
		}
//		UserModel userModel = Application.context.getBean(UserModel.class);
//		RoleModel roleModel = Application.context.getBean(RoleModel.class);
		UserVO userVO = userModel.findOrInit(request.getPlatId() ,request.getServerId(), request.getOpenId());
		gameSession.setUser(userVO);
		if(userVO != null){
			gameSession.setLogin(true);
			gameSession.setUser(userVO);
			String uid = userVO.db.getId();
			User user = userVO.db;
			RoleVO role = roleModel.getRoleByUid(uid, gameSession);
			if(role == null){
				gameSession.sendMsg(new LoginResponse(201, user.getPlatId(), user.getServerId(), user.getOpenId(), request.getToken(),user.getId()));
			}
			else{
				gameSession.setRole(role);
				gameSession.sendMsg(new LoginResponse(200, user.getPlatId(), user.getServerId(), user.getOpenId(), request.getToken(),user.getId()));
				enterWorldController.execute(gameSession);
			}
		}else{
			
		}
	}
	private boolean checkToken(LoginRequest request) {
		return true;
	}

}
