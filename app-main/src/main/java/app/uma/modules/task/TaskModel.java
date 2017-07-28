package app.uma.modules.task;

import org.springframework.stereotype.Component;

import app.uma.base.ModelBase;
import app.uma.net.socket.sessions.GameSession;

@Component
public class TaskModel extends ModelBase {

	@Override
	public void startup() {
		
	}

	@Override
	public void registProsesser() {
//		registProcess(ProtocolConst., PackInitProcesser.class);
	}
	
	public void getTaskInfo(GameSession session){
		
	}
}
