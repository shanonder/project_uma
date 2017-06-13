package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class LoginGateResponse  extends ServerResponse{
	public LoginGateResponse(int status , String uid, String token, String server, int port) throws Exception{
		super(0x10000 , status);
		output.writeUTF(uid == null ? "" : uid);
		output.writeUTF(token == null ? "" : token);
		output.writeUTF(server == null ? "" : server);
		output.writeInt(port);

	}


}