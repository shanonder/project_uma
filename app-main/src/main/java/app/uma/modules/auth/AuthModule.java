package app.uma.modules.auth;

import app.uma.modules.IModule;
import app.uma.modules.ModuleBase;
import app.uma.modules.auth.processer.LoginProcesser;
import app.uma.net.socket.consts.ProtocolConst;

public class AuthModule extends ModuleBase implements IModule {

	@Override
	public void startup() {
		registProcess(ProtocolConst.LoginRequest, new LoginProcesser());
	}

}
