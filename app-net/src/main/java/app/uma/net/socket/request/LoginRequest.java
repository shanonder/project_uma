package app.uma.net.socket.request;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class LoginRequest{
	public LoginRequest(ClientRequest request) throws Exception{
		platId = request.getShort();
		serverId = request.getInt();
		openId = request.getString();
		token = request.getString();

	}

	private int platId;//登录平台
	private int serverId;//服务器ID
	private String openId;//玩家第三方ID
	private String token;//通行证
	public int getPlatId(){
		return this.platId;
	}
	public void setPlatId(int platId){
		this.platId=platId;
	}
	public int getServerId(){
		return this.serverId;
	}
	public void setServerId(int serverId){
		this.serverId=serverId;
	}
	public String getOpenId(){
		return this.openId;
	}
	public void setOpenId(String openId){
		this.openId=openId;
	}
	public String getToken(){
		return this.token;
	}
	public void setToken(String token){
		this.token=token;
	}

}