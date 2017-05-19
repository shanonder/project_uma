package app.uma.net.socket.message;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.sessions.GameSession;

/**
 * 消息分发器，根据消息号，找到相应的消息处理器
 *
 */
public class MsgDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(MsgDispatcher.class);
	
	private Map<Integer, MsgProcessor> processorsMap = new HashMap<Integer, MsgProcessor>();
	
	private static MsgDispatcher instance;
	public static MsgDispatcher getInstance() {
		// TODO Auto-generated method stub
		if(instance == null){
			instance = new MsgDispatcher();
		}
		return instance;
	}

	public MsgDispatcher(){
		
	}
	
	public void registProcess(int cmd,MsgProcessor progress){
		if(getMsgProcessor(cmd) != null){
			logger.warn("消息号监听重复："+ cmd);
		}
		processorsMap.put(cmd, progress);
	}
	
	public MsgProcessor getMsgProcessor(int msgCode){
		return processorsMap.get(msgCode);
	}
	
	public void dispatchMsg( GameSession gameSession,ClientRequest clientRequest) {
		
		int msgCode = clientRequest.getCmd();
		MsgProcessor processor = getMsgProcessor(msgCode);
		if(gameSession.isLogin() || processor instanceof INotAuthProcessor){
			processor.handle(gameSession, clientRequest);
		}
		
	}

}
