package app.uma.net.socket.responses;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由protocol_generate_util自动生成
 */
public class LoginGameResponse  extends ServerResponse{
	public LoginGameResponse(int status , int type) throws Exception{
		super(0x10002 , status);
		output.writeShort(type);

	}


}