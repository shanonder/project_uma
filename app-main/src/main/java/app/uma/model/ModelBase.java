/**
 *
 * author: shanonder
 * date: 2017年6月18日 下午12:47:32
 *
 */
package app.uma.model;

import org.springframework.beans.factory.annotation.Autowired;

import app.uma.Application;
import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgDispatcher;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.sessions.GameSession;

public abstract class ModelBase{
	@Autowired
	private MsgDispatcher msgDispatcher;
	
	private boolean isInit;
	final public void initCfgs(){
		if(isInit){
			return;
		}
		isInit = true;
		initCfg();
		
	}
	
	final public void startup(){
		registProsesser();
	}
	


	protected abstract void initCfg();
	public abstract void registProsesser();
	
	public final void registProcess(int cmd,MsgProcessor progress){
		msgDispatcher.registProcess(cmd, progress);
	}
	
	public final void registProcess(int cmd,Class<? extends MsgProcessor> proClass){
		msgDispatcher.registProcess(cmd, Application.context.getBean(proClass));
	}
	public final void dispatchMsg( GameSession gameSession,ClientRequest clientRequest){
		msgDispatcher.dispatchMsg(gameSession, clientRequest);
	}
	
}
