package app.uma.net.socket.requests;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由protocol_generate_util自动生成
 */
public class LoginGameRequest{
	public LoginGameRequest(ClientRequest request) throws Exception{
		passKey = request.getString();

	}

	private String passKey;//通行证
	public String getPassKey(){
		return this.passKey;
	}
	public void setPassKey(String passKey){
		this.passKey=passKey;
	}

}