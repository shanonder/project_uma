package app.uma.net.socket.services;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class GateRoutor {
	private static GateRoutor instance;
	private HashMap<String, IoSession> clientMap;
	private final Logger logger = Logger.getLogger(getClass());
	public GateRoutor() {

	}

	public static GateRoutor getInstance() {
		if (instance == null){
			instance = new GateRoutor();
			instance.init();
		}
			
		return instance;
	}

	private void init() {
		HashMap<String, IoSession> clientMap = new HashMap<String, IoSession>();
		this.clientMap = clientMap;
	}
	
	public boolean tryLogin(String key, IoSession session){
		if(clientMap.containsKey(key)){
			logger.info("hasOldKey");
		}
		clientMap.put(key, session);
		return true;
	}
	
	public void remove(String key){
		if(clientMap.containsKey(key)){
			clientMap.remove(key);
		}
	}

//	public LoginGateResponse initInfo(TbUser user) throws Exception {
//		LoginGateResponse lgr = new LoginGateResponse(200, "127.0.0.1", 3005, user.getInsid());
//		return lgr;
//	}
}
