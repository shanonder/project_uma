package app.uma.net.socket.heaps;


import org.springframework.stereotype.Component;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.response.HeapResponse;
import app.uma.net.socket.sessions.GameSession;

@Component
public class HeapProcesser extends MsgProcessor{

	@Override
	public void process(GameSession gameSession, ClientRequest request) throws Exception {
		gameSession.sendMsg(new HeapResponse(200, System.currentTimeMillis()));
	}

}
