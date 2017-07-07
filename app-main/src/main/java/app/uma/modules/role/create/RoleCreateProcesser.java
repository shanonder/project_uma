package app.uma.modules.role.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.controller.EnterWorldController;
import app.uma.modules.role.RoleModel;
import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.request.RoleCreateRequest;
import app.uma.net.socket.sessions.GameSession;

@Component
public class RoleCreateProcesser extends MsgProcessor {

	@Autowired
	private RoleModel roleModel;
	@Override
	public void process(GameSession gameSession, ClientRequest cr) throws Exception {
		RoleCreateRequest request = new RoleCreateRequest(cr);
//		RoleModel roleModel = Application.context.getBean(RoleModel.class);
		int state = roleModel.create(request, gameSession);
		if(state == 200){
			EnterWorldController.execute(gameSession);
		}
	}

}
