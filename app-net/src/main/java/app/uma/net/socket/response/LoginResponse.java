package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class LoginResponse  extends ServerResponse{
	public LoginResponse(int status , String uid, String token, int platId) throws Exception{
		super(0x10001 , status);
		output.writeUTF(uid == null ? "" : uid);
		output.writeUTF(token == null ? "" : token);
		output.writeShort(platId);

	}


}