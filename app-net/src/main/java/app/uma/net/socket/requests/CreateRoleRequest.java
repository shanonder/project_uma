package app.uma.net.socket.requests;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由protocol_generate_util自动生成
 */
public class CreateRoleRequest{
	public CreateRoleRequest(ClientRequest request) throws Exception{
		name = request.getString();
		cfgId = request.getString();

	}

	private String name;
	private String cfgId;
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getCfgId(){
		return this.cfgId;
	}
	public void setCfgId(String cfgId){
		this.cfgId=cfgId;
	}

}