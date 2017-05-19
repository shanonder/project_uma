package app.uma.net.socket.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.sessions.GameSession;

public abstract class MsgProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(MsgProcessor.class);
	
	public void handle(GameSession gameSession,ClientRequest request){
		try {
			process(gameSession,request);
		} catch (Exception e) {
			logger.error("消息处理出错，msg code:"+request.getCmd());
			e.printStackTrace();
		}
	}
	
	public abstract void process(GameSession gameSession,ClientRequest request)throws Exception;
}
