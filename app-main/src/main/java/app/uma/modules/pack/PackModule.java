package app.uma.modules.pack;

import app.uma.modules.ModuleBase;
import app.uma.modules.pack.processer.PackDeleteProcesser;
import app.uma.modules.pack.processer.PackInitProcesser;
import app.uma.modules.pack.processer.PackMoveProcesser;
import app.uma.modules.pack.processer.PackSellProcesser;
import app.uma.net.socket.consts.ProtocolConst;

public class PackModule extends ModuleBase{
	
	@Override
	public void startup(){
		registProcess(ProtocolConst.PackInitRequest, new PackInitProcesser());
		registProcess(ProtocolConst.PackMoveRequest, new PackMoveProcesser());
		registProcess(ProtocolConst.PackDeleteRequest, new PackDeleteProcesser());
		registProcess(ProtocolConst.PackSellRequest, new PackSellProcesser());
	}
}
