package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
import app.uma.net.socket.data.RoleData;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class RoleEnterWorldResponse  extends ServerResponse{

	/**
	 * @struct int 状态  200 正常
	 * @mapId short 
	 * @posX double 
	 * @posY double 
	 * @role RoleData 角色信息
	 */
	public RoleEnterWorldResponse(int status , int mapId, double posX, double posY, RoleData role) throws Exception{
		super(0x30002 , status);
		output.writeShort(mapId);
		output.writeDouble(posX);
		output.writeDouble(posY);
		output.writeData(role == null ? new Object() : role);

	}


}