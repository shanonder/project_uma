package app.uma.modules.pack.processer;

import org.springframework.stereotype.Component;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.request.PackMoveRequest;
import app.uma.net.socket.sessions.GameSession;

@Component
public class PackMoveProcesser  extends MsgProcessor {

	@Override
	public void process(GameSession gameSession, ClientRequest cr) throws Exception {
		PackMoveRequest request = new PackMoveRequest(cr);
		
	}

}
