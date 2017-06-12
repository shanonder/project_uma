package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
import java.util.ArrayList;
import app.uma.net.socket.data.RoleData;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class LoginResponse  extends ServerResponse{
	public LoginResponse(int status , String uid, String token, String server, int port, ArrayList<RoleData> roles, String lastRoleId) throws Exception{
		super(0x10001 , status);
		output.writeUTF(uid == null ? "" : uid);
		output.writeUTF(token == null ? "" : token);
		output.writeUTF(server == null ? "" : server);
		output.writeInt(port);
		output.writeAMFObject(roles == null ? new ArrayList<>() : roles);
		output.writeUTF(lastRoleId == null ? "" : lastRoleId);

	}


}