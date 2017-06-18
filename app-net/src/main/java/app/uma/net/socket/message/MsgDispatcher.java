package app.uma.net.socket.message;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.interfaces.INotAuthProcessor;
import app.uma.net.socket.sessions.GameSession;

/**
 * 消息分发器，根据消息号，找到相应的消息处理器
 *
 */
@Component
public class MsgDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(MsgDispatcher.class);
	
	private Map<Integer, MsgProcessor> processorsMap = new HashMap<Integer, MsgProcessor>();
	
	public void registProcess(int cmd,MsgProcessor progress){
		if(getMsgProcessor(cmd) != null){
			logger.warn("消息号监听重复：0x"+ Integer.toHexString(cmd));
		}
		processorsMap.put(cmd, progress);
	}
	
	public MsgProcessor getMsgProcessor(int msgCode){
		return processorsMap.get(msgCode);
	}
	
	public void dispatchMsg( GameSession gameSession,ClientRequest clientRequest) {
		
		int msgCode = clientRequest.getCmd();
		MsgProcessor processor = getMsgProcessor(msgCode);
		if(processor == null){
			logger.warn("消息未监听：0x" + Integer.toHexString(msgCode));
		}
		if(gameSession.isLogin() || processor instanceof INotAuthProcessor){
			processor.handle(gameSession, clientRequest);
		}
		
	}

}
