package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class RoleCreateResponse  extends ServerResponse{

	/**
	 * @struct int 状态  200 正常
	 */
	public RoleCreateResponse(int status ) throws Exception{
		super(0x30001 , status);

	}


}