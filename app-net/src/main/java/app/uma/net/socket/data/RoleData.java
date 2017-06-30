package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import app.uma.net.socket.data.AttributesData;
import app.uma.net.socket.util.ArrayUtil;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:6fbd4498441778567949fafa0192e410
	 */
public class RoleData implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一ID
	 */
	private String insId;

	/**
	 * 职业ID
	 */
	private int profId;

	/**
	 * 服务器ID
	 */
	private int serverId;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 等级
	 */
	private int level;

	/**
	 * 经验
	 */
	private double exp;

	/**
	 * 属性
	 */
	private ArrayList<AttributesData> attributes;

	/**
	 * 唯一ID
	 */
	public String getInsId(){
		return this.insId;
	}
	public void setInsId(String insId){
		this.insId=insId;
	}

	/**
	 * 职业ID
	 */
	public int getProfId(){
		return this.profId;
	}
	public void setProfId(int profId){
		this.profId=profId;
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
	 * 姓名
	 */
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}

	/**
	 * 等级
	 */
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}

	/**
	 * 经验
	 */
	public double getExp(){
		return this.exp;
	}
	public void setExp(double exp){
		this.exp=exp;
	}

	/**
	 * 属性
	 */
	public ArrayList<AttributesData> getAttributes(){
		return this.attributes;
	}
	public void setAttributes(ArrayList<AttributesData> attributes){
		this.attributes=attributes;
	}


	public static void write(DataOutputStream out , RoleData data) throws Exception{
		out.writeUTF(data.insId == null ?"" : data.insId);
		out.writeInt(data.profId);
		out.writeShort(data.serverId);
		out.writeUTF(data.name == null ?"" : data.name);
		out.writeInt(data.level);
		out.writeDouble(data.exp);
		ArrayUtil.write( out , data.attributes);
	}

	@SuppressWarnings("unchecked")
	public static RoleData read(DataInputStream in , RoleData data) throws Exception{
		if(data == null){
			data = new RoleData();
		}
		data.insId = in.readUTF();
		data.profId = in.readInt();
		data.serverId = in.readShort();
		data.name = in.readUTF();
		data.level = in.readInt();
		data.exp = in.readDouble();
		data.attributes = ArrayUtil.read(in);
		return data;
	}

}