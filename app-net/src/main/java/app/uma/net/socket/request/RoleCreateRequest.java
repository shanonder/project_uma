package app.uma.net.socket.request;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class RoleCreateRequest{
	public RoleCreateRequest(ClientRequest request) throws Exception{
		name = request.getString();
		profId = request.getShort();

	}

	private String name;//角色姓名
	private int profId;//职业id
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getProfId(){
		return this.profId;
	}
	public void setProfId(int profId){
		this.profId=profId;
	}

}