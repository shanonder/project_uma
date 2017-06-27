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

	/**
	 * 登录平台
	 */
	private int platId;

	/**
	 * 服务器ID
	 */
	private int serverId;

	/**
	 * 玩家第三方ID
	 */
	private String openId;

	/**
	 * 通行证
	 */
	private String token;

	/**
	 * 登录平台
	 */
	public int getPlatId(){
		return this.platId;
	}
	public void setPlatId(int platId){
		this.platId=platId;
	}

	/**
	 * 服务器ID
	 */
	public int getServerId(){
		return this.serverId;
	}
	public void setServerId(int serverId){
		this.serverId=serverId;
	}

	/**
	 * 玩家第三方ID
	 */
	public String getOpenId(){
		return this.openId;
	}
	public void setOpenId(String openId){
		this.openId=openId;
	}

	/**
	 * 通行证
	 */
	public String getToken(){
		return this.token;
	}
	public void setToken(String token){
		this.token=token;
	}


}