package app.uma.net.socket.responses;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由protocol_generate_util自动生成
 */
public class LoginGateResponse  extends ServerResponse{
	public LoginGateResponse(int status , String address, int port, String passKey) throws Exception{
		super(0x10001 , status);
		output.writeUTF(address == null ? "" : address);
		output.writeInt(port);
		output.writeUTF(passKey == null ? "" : passKey);

	}


}