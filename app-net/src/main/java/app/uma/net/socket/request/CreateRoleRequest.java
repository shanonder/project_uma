package app.uma.net.socket.request;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class CreateRoleRequest{
	public CreateRoleRequest(ClientRequest request) throws Exception{
		name = request.getString();
		profession = request.getInt();

	}

	private String name;//角色名字
	private int profession;//职业
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getProfession(){
		return this.profession;
	}
	public void setProfession(int profession){
		this.profession=profession;
	}

}