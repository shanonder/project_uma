package app.uma.net.socket.heaps;


import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.responses.HeapResponse;
import app.uma.net.socket.sessions.GameSession;

public class HeapProcesser extends MsgProcessor{

	@Override
	public void process(GameSession gameSession, ClientRequest request) throws Exception {
		gameSession.sendMsg(new HeapResponse(200, System.currentTimeMillis()));
	}

}
