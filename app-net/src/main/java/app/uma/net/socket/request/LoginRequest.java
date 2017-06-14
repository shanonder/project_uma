package app.uma.net.socket.request;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class LoginRequest{
	public LoginRequest(ClientRequest request) throws Exception{
		platId = request.getShort();
		appkey = request.getString();
		token = request.getString();

	}

	private int platId;//登录平台
	private String appkey;//玩家appId
	private String token;//通行证
	public int getPlatId(){
		return this.platId;
	}
	public void setPlatId(int platId){
		this.platId=platId;
	}
	public String getAppkey(){
		return this.appkey;
	}
	public void setAppkey(String appkey){
		this.appkey=appkey;
	}
	public String getToken(){
		return this.token;
	}
	public void setToken(String token){
		this.token=token;
	}

}