package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
import app.uma.net.socket.data.RoleData;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class RoleEnterWorldResponse  extends ServerResponse{
	public RoleEnterWorldResponse(int status , int mapId, double posX, double posY, RoleData role) throws Exception{
		super(0x30002 , status);
		output.writeInt(mapId);
		output.writeDouble(posX);
		output.writeDouble(posY);
		output.writeAMFObject(role == null ? new Object() : role);

	}


}