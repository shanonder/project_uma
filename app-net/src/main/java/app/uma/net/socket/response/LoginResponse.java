package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class LoginResponse  extends ServerResponse{
	public LoginResponse(int status , int platId, String appkey, String token, String uid) throws Exception{
		super(0x10001 , status);
		output.writeShort(platId);
		output.writeUTF(appkey == null ? "" : appkey);
		output.writeUTF(token == null ? "" : token);
		output.writeUTF(uid == null ? "" : uid);

	}


}