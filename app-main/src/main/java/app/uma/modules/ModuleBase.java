package app.uma.modules;

import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgDispatcher;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.sessions.GameSession;

public abstract class ModuleBase implements IModule{
	
	private MsgDispatcher msgDispatcher;
	public ModuleBase() {
		msgDispatcher = MsgDispatcher.getInstance();
	}
	
	public void startup(){
		
	}
	
	public final void registProcess(int cmd,MsgProcessor progress){
		msgDispatcher.registProcess(cmd, progress);
	}
	public void dispatchMsg( GameSession gameSession,ClientRequest clientRequest){
		msgDispatcher.dispatchMsg(gameSession, clientRequest);
	}
}
