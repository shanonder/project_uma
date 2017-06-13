package app.uma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.uma.net.socket.consts.ProtocolConst;
import app.uma.net.socket.heaps.HeapProcesser;
import app.uma.net.socket.interfaces.IModuleFacade;
import app.uma.net.socket.message.MsgDispatcher;

public class ModuleFacade implements IModuleFacade {

	private static final Logger log = LoggerFactory.getLogger(ModuleFacade.class);
	@Override
	public void start() {
		MsgDispatcher.getInstance().registProcess(ProtocolConst.HeapRequest, new HeapProcesser());
	}

}
