package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import app.uma.net.socket.data.RoleData;
import app.uma.net.socket.util.ArrayUtil;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:48de6c395c61af6835561193f7f2a6db
	 */
public class UserInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String insId;//唯一ID
	private ArrayList<RoleData> roles;//角色列表
	private String lastRoleId;//上次登录角色ID
	public String getInsId(){
		return this.insId;
	}
	public void setInsId(String insId){
		this.insId=insId;
	}
	public ArrayList<RoleData> getRoles(){
		return this.roles;
	}
	public void setRoles(ArrayList<RoleData> roles){
		this.roles=roles;
	}
	public String getLastRoleId(){
		return this.lastRoleId;
	}
	public void setLastRoleId(String lastRoleId){
		this.lastRoleId=lastRoleId;
	}

	public static void write(DataOutputStream out , UserInfo data) throws Exception{
		out.writeUTF(data.insId == null ?"" : data.insId);
		ArrayUtil.write( out , data.roles);
		out.writeUTF(data.lastRoleId == null ?"" : data.lastRoleId);
	}

	@SuppressWarnings("unchecked")
	public static UserInfo read(DataInputStream in , UserInfo data) throws Exception{
		data.insId = in.readUTF();
		data.roles = ArrayUtil.read(in);
		data.lastRoleId = in.readUTF();
		return data;
	}

}