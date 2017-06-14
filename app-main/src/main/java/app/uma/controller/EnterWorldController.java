/**
 *
 * author: shanonder
 * date: 2017年6月14日 下午8:25:51
 *
 */
package app.uma.controller;

import app.uma.Application;
import app.uma.model.PackModel;
import app.uma.model.WorldModel;
import app.uma.net.socket.sessions.GameSession;

public class EnterWorldController {
	public static void execute(GameSession session){
		WorldModel worldModel = Application.context.getBean(WorldModel.class);
		worldModel.enterWorld(session);
		
		PackModel packModel = Application.context.getBean(PackModel.class);
		packModel.init(session);
		
	}
}
