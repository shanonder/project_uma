package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import app.uma.net.socket.data.AttributesData;
import app.uma.net.socket.util.ArrayUtil;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:9ff1b2f52a6c4f172053a9e30951340
	 */
public class RoleData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String insId;//唯一ID
	private int cfgId;//配置表ID
	private String name;//姓名
	private int level;//等级
	private long exp;//经验
	private ArrayList<AttributesData> attributes;//属性
	public String getInsId(){
		return this.insId;
	}
	public void setInsId(String insId){
		this.insId=insId;
	}
	public int getCfgId(){
		return this.cfgId;
	}
	public void setCfgId(int cfgId){
		this.cfgId=cfgId;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public long getExp(){
		return this.exp;
	}
	public void setExp(long exp){
		this.exp=exp;
	}
	public ArrayList<AttributesData> getAttributes(){
		return this.attributes;
	}
	public void setAttributes(ArrayList<AttributesData> attributes){
		this.attributes=attributes;
	}

	public static void write(DataOutputStream out,RoleData data) throws Exception{
		out.writeUTF(data.insId == null ?"" : data.insId);
		out.writeInt(data.cfgId);
		out.writeUTF(data.name == null ?"" : data.name);
		out.writeInt(data.level);
		out.writeLong(data.exp);
		ArrayUtil.write(out,data.attributes);
	}

	@SuppressWarnings("unchecked")
	public static RoleData read(DataInputStream in , RoleData data) throws Exception{
		data.insId = in.readUTF();
		data.cfgId = in.readInt();
		data.name = in.readUTF();
		data.level = in.readInt();
		data.exp = in.readLong();
		data.attributes = ArrayUtil.read(in);
		return data;
	}

}