package app.uma.modules.role.create;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.request.RoleCreateRequest;
import app.uma.net.socket.sessions.GameSession;

public class RoleCreateProcesser extends MsgProcessor {


	@Override
	public void process(GameSession gameSession, ClientRequest cr) throws Exception {
		RoleCreateRequest request = new RoleCreateRequest(cr);
		
	}

}
