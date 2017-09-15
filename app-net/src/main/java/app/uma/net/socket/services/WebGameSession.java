package app.uma.net.socket.services;

import org.apache.mina.core.session.IoSession;

import app.uma.net.socket.sessions.GameSession;

public class WebGameSession extends GameSession {

	public boolean isHandshaked;
	public WebGameSession(IoSession session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

}
