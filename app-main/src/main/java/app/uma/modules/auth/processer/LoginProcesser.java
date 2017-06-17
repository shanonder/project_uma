package app.uma.modules.auth.processer;

import app.uma.Application;
import app.uma.controller.EnterWorldController;
import app.uma.dao.entity.User;
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
		UserVO userVO = userModel.findOrInit(request.getPlatId() ,request.getServerId(), request.getOpenId());
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
				EnterWorldController.execute(gameSession);
			}
		}else{
			
		}
	}
	private boolean checkToken(LoginRequest request) {
		return true;
	}

}
