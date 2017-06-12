package app.uma.net.socket.request;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class LoginGateRequest{
	public LoginGateRequest(ClientRequest request) throws Exception{
		uid = request.getString();
		token = request.getString();
		platform = request.getString();

	}

	private String uid;//玩家ID
	private String token;//通行证
	private String platform;//登录平台
	public String getUid(){
		return this.uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}
	public String getToken(){
		return this.token;
	}
	public void setToken(String token){
		this.token=token;
	}
	public String getPlatform(){
		return this.platform;
	}
	public void setPlatform(String platform){
		this.platform=platform;
	}

}