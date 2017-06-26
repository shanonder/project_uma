/**
 *
 * author: shanonder
 * date: 2017年6月18日 下午12:47:32
 *
 */
package app.uma.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import app.uma.Application;
import app.uma.net.socket.decodes.ClientRequest;
import app.uma.net.socket.message.MsgDispatcher;
import app.uma.net.socket.message.MsgProcessor;
import app.uma.net.socket.sessions.GameSession;

public abstract class ModelBase{
	
	
	private static final Logger log = LoggerFactory.getLogger(ModelBase.class);

	@Autowired
	private MsgDispatcher msgDispatcher;
	
	
	

	public abstract void startup();
	public abstract void registProsesser();
	
	public final void registProcess(int cmd,MsgProcessor progress){
		msgDispatcher.registProcess(cmd, progress);
	}
	
	public final void registProcess(int cmd,Class<? extends MsgProcessor> procClass){
		MsgProcessor processor = Application.context.getBean(procClass);
		if(processor == null){
			log.warn("regist error: " + procClass.getName());
			return;
		}
		msgDispatcher.registProcess(cmd, processor);
	}
	public final void dispatchMsg( GameSession gameSession , ClientRequest clientRequest){
		msgDispatcher.dispatchMsg(gameSession, clientRequest);
	}
	
}
