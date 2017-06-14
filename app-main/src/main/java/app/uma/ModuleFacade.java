package app.uma;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.uma.modules.IModule;
import app.uma.modules.auth.AuthModule;
import app.uma.modules.role.create.RoleCreateModule;
import app.uma.net.socket.consts.ProtocolConst;
import app.uma.net.socket.heaps.HeapProcesser;
import app.uma.net.socket.interfaces.IModuleFacade;
import app.uma.net.socket.message.MsgDispatcher;

public class ModuleFacade implements IModuleFacade {

	private static final Logger log = LoggerFactory.getLogger(ModuleFacade.class);
	private ArrayList<IModule> modules;
	public ModuleFacade() {
		modules = new ArrayList<>();
		modules.add(new AuthModule());
		modules.add(new RoleCreateModule());
	}
	@Override
	public void start() {
		MsgDispatcher.getInstance().registProcess(ProtocolConst.HeapRequest, new HeapProcesser());
		
		for(IModule module : modules){
			module.startup();
		}
	}

}
