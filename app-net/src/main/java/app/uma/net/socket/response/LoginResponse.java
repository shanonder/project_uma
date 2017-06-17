package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class LoginResponse  extends ServerResponse{
	public LoginResponse(int status , int platId, int serverId, String openId, String token, String uid) throws Exception{
		super(0x10001 , status);
		output.writeShort(platId);
		output.writeInt(serverId);
		output.writeUTF(openId == null ? "" : openId);
		output.writeUTF(token == null ? "" : token);
		output.writeUTF(uid == null ? "" : uid);

	}


}