/**
 *
 * author: shanonder
 * date: 2017年6月14日 下午8:25:51
 *
 */
package app.uma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.modules.pack.PackModel;
import app.uma.modules.role.RoleVO;
import app.uma.modules.scene.WorldModel;
import app.uma.net.socket.sessions.GameSession;

@Component
public class EnterWorldController {
	@Autowired
	private WorldModel worldModel;
	
	@Autowired
	private PackModel packModel;
	public void execute(GameSession session) throws Exception{
		worldModel.enterWorld(session);
		packModel.getBySession(session);
		
	}
}
