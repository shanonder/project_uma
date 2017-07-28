package app.uma.net.socket.handlers;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgDispatcher;
import app.uma.net.socket.sessions.GameSession;

@Component
public class AppSocketIoHandler extends IoHandlerAdapter {
	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	MsgDispatcher msgDispatcher;
	
	@Override
	public void sessionOpened(IoSession session) {
		@SuppressWarnings("unused")
		GameSession gs = new GameSession(session);
//		todo
	}
	@Override
	public void messageReceived(IoSession session, Object message) {
		ClientRequest clientRequest = (ClientRequest)message;
		GameSession gs = GameSession.getInstance(session);
		if (gs == null) {
			return;
		}
		msgDispatcher.dispatchMsg(gs,clientRequest);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		logger.info("Disconnecting the idle.");
		session.closeNow();
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		// close the connection on exceptional situation
//		logger.warn(cause.getMessage(), cause);
		session.closeNow();
		logger.info("session closed");
	}
}
