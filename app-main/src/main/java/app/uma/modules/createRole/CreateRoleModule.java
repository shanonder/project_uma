package app.uma.modules.createRole;

import app.uma.net.socket.consts.ProtocolConst;
import app.uma.net.socket.message.MsgDispatcher;
import app.uma.processer.CreateRoleProcesser;

public class CreateRoleModule {
	public void startup(){
		
//		MsgDispatcher.getInstance().registProcess(ProtocolConst.LoginRequest,new LoginGameProcesser());
		MsgDispatcher.getInstance().registProcess(ProtocolConst.LoginRequest,new CreateRoleProcesser());
		
	}
}
