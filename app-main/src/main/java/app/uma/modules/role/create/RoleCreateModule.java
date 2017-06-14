package app.uma.modules.role.create;

import app.uma.modules.ModuleBase;
import app.uma.net.socket.consts.ProtocolConst;

public class RoleCreateModule extends ModuleBase {
	public void startup(){
		registProcess(ProtocolConst.RoleCreateRequest , new RoleCreateProcesser());
	}
}
