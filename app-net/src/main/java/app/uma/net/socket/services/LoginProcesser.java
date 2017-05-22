package app.uma.net.socket.services;



import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.interfaces.INotAuthProcessor;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.requests.LoginGateRequest;
import app.uma.net.socket.sessions.GameSession;

public class LoginProcesser extends MsgProcessor implements INotAuthProcessor{

	@Override
	public void process(GameSession gameSession, ClientRequest request) throws Exception {
		LoginGateRequest loginRequest = new LoginGateRequest(request);
//		TbUser user = UserModel.getInstance().login(loginRequest.getUsername(), loginRequest.getPassword());
//		if(user != null){
//			GateRoutor gateRoutor = GateRoutor.getInstance();
//			gameSession.sendMsg(gateRoutor.initInfo(user));
//		}else{
//			gameSession.sendMsg(new LoginGateResponse(101,null,0,null));
//		}
	}

}
