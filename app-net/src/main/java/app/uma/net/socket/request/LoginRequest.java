package app.uma.net.socket.request;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class LoginRequest{
	public LoginRequest(ClientRequest request) throws Exception{
		uid = request.getString();
		token = request.getString();
		platId = request.getShort();

	}

	private String uid;//玩家ID
	private String token;//通行证
	private int platId;//登录平台
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
	public int getPlatId(){
		return this.platId;
	}
	public void setPlatId(int platId){
		this.platId=platId;
	}

}