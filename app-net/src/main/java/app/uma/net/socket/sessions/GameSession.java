package app.uma.net.socket.sessions;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;

import app.uma.net.socket.interfaces.ResponseMsg;
/**
 * 游戏中的session回话，封装了mina的session
 *
 */
public class GameSession{

	private IoSession session;
	private WebSocketSession wsSession;
	
	private String address;
	
	private Object user;
	
	private Object role;
	
	private ArrayList<?> pack;
	
	private boolean isLogin=false;
	
	private static final AttributeKey KEY_PLAYER_SESSION = new AttributeKey(GameSession.class, "user.session");

	private static final ConcurrentHashMap<String,GameSession> wsMap = new ConcurrentHashMap<>();
	public GameSession(IoSession session){
		this.session = session;
		this.session.setAttribute(KEY_PLAYER_SESSION, this);
		SocketAddress socketaddress = session.getRemoteAddress();
		InetSocketAddress s = (InetSocketAddress) socketaddress;
		address = s.getAddress().getHostAddress();
	}
	
	public GameSession(WebSocketSession wsSession){
		this.wsSession = wsSession;
		wsMap.put(wsSession.getId(),this);
		SocketAddress socketaddress = wsSession.getRemoteAddress();
		InetSocketAddress s = (InetSocketAddress) socketaddress;
		address = s.getAddress().getHostAddress();
	}
	
	public static GameSession getInstance(IoSession session) {
		Object playerObj = session.getAttribute(KEY_PLAYER_SESSION);
		return (GameSession) playerObj;
	}
	
	public static GameSession getInstance(WebSocketSession session) {
		return wsMap.get(session.getId());
	}
	
	/**
	 * 发送消息给客户端
	 * @param msg
	 * @return
	 */
	public WriteFuture sendMsg(ResponseMsg msg) {
		if (session != null && session.isConnected() && !session.isClosing()) {
			return session.write(msg);
		}
		else if (wsSession != null && wsSession.isOpen()) {
			try {
				wsSession.sendMessage(new BinaryMessage(msg.entireMsg().buf()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}
	
	public String getAddress(){
		return this.address;
	}
	
	public  void setLogin(boolean isLogin){
		this.isLogin=isLogin;
	}
	public boolean isLogin(){
		return this.isLogin;
	}
	
	public void setUser(Object obj){
		this.user = obj;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getUser(Class<T> t){
		return (T)this.user;
	}
	
	public void setRole(Object obj){
		this.role = obj;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getRole(Class<T> t){
		return (T)this.role;
	}
	public void close(){
		if(session != null){
			session.closeOnFlush();
		}
		if(wsSession != null){
			try {
				wsSession.close();
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @param <T>
	 * @return the pack
	 */
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> getPack(Class<T> t) {
		return (ArrayList<T>)this.pack;
	}

	/**
	 * @param <T>
	 * @param pack the pack to set
	 */
	public <T> void setPack(ArrayList<T> value) {
		this.pack = value;
	}
	
}
