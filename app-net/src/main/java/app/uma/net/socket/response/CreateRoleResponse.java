package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class CreateRoleResponse  extends ServerResponse{
	public CreateRoleResponse(int status , String name, int profession, int state) throws Exception{
		super(0x2001 , status);
		output.writeUTF(name == null ? "" : name);
		output.writeInt(profession);
		output.writeInt(state);

	}


}