package app.uma.net.socket.requests;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由protocol_generate_util自动生成
 */
public class LoginGateRequest{
	public LoginGateRequest(ClientRequest request) throws Exception{
		username = request.getString();
		password = request.getString();

	}

	private String username;//用户名
	private String password;//密码
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}

}