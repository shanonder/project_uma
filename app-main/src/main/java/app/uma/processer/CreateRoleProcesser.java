package app.uma.processer;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.request.CreateRoleRequest;
import app.uma.net.socket.sessions.GameSession;

public class CreateRoleProcesser extends MsgProcessor {


	@Override
	public void process(GameSession gameSession, ClientRequest cr) throws Exception {
		CreateRoleRequest request = new CreateRoleRequest(cr);
		
	}

}
