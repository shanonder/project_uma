package app.uma.net.socket.handlers;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.interfaces.MsgProtocol;
import app.uma.net.socket.message.MsgDispatcher;
import app.uma.net.socket.sessions.GameSession;

public class AppWebSocketHandler implements WebSocketHandler {
	 
	private Logger log = LoggerFactory.getLogger(AppWebSocketHandler.class);
	
 
	@Autowired
	MsgDispatcher msgDispatcher;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	GameSession gs = new GameSession(session);
    }
 
    
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
    	log.info("handleMessage: " + message.toString());
        //sendMessageToUsers();
    	GameSession gs = GameSession.getInstance(session);
		if (gs == null) {
			return;
		}
		BinaryMessage msg = (BinaryMessage)message;
		ByteBuffer buffer = msg.getPayload();
		ClientRequest request = new ClientRequest(buffer.array());
		int head = buffer.getShort();//2 version todo
		int v = buffer.getShort();//2 version todo
		int l = buffer.getInt();//4
		int bl = l - MsgProtocol.headSize;
		int remains = buffer.remaining();
		byte[] bytes = new byte[bl];
		request = new ClientRequest(bytes);
		request.cmd = buffer.getInt();
		request.status = buffer.getShort();
		request.testid = buffer.getInt();
		request.reserved = buffer.getInt();
		if(bl > 0){
			buffer.get(bytes, 0 , bl);
		}
		
		msgDispatcher.dispatchMsg(gs,request);
//    	session.sendMessage(new TextMessage(new Date() + ""));
    }
 
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
//        users.remove(session);
        
        log.info("handleTransportError" + exception.getMessage());
    }
 
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
//        users.remove(session);
        log.debug("afterConnectionClosed" + closeStatus.getReason());
        
    }
 
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
 
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
//        for (WebSocketSession user : users) {
//            try {
//                if (user.isOpen()) {
//                    user.sendMessage(message);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
 
}