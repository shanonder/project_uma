package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:6fbd4498441778567949fafa0192e410
	 */
public class ItemData implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一ID
	 */
	private String insId;

	/**
	 * 配置表ID
	 */
	private int cfgId;

	/**
	 * 分类
	 */
	private String type;

	/**
	 * 创建时间
	 */
	private double createTime;

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
	 * 配置表ID
	 */
	public int getCfgId(){
		return this.cfgId;
	}
	public void setCfgId(int cfgId){
		this.cfgId=cfgId;
	}

	/**
	 * 分类
	 */
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type=type;
	}

	/**
	 * 创建时间
	 */
	public double getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(double createTime){
		this.createTime=createTime;
	}


	public static void write(DataOutputStream out , ItemData data) throws Exception{
		out.writeUTF(data.insId == null ?"" : data.insId);
		out.writeInt(data.cfgId);
		out.writeUTF(data.type == null ?"" : data.type);
		out.writeDouble(data.createTime);
	}

	@SuppressWarnings("unchecked")
	public static ItemData read(DataInputStream in , ItemData data) throws Exception{
		if(data == null){
			data = new ItemData();
		}
		data.insId = in.readUTF();
		data.cfgId = in.readInt();
		data.type = in.readUTF();
		data.createTime = in.readDouble();
		return data;
	}

}