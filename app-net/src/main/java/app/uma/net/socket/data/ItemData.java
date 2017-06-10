package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:9ff1b2f52a6c4f172053a9e30951340
	 */
public class ItemData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String insId;//唯一ID
	private int cfgId;//配置表ID
	private String type;//分类
	private long createTime;//创建时间
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
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type=type;
	}
	public long getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(long createTime){
		this.createTime=createTime;
	}

	public static void write(DataOutputStream out , ItemData data) throws Exception{
		out.writeUTF(data.insId == null ?"" : data.insId);
		out.writeInt(data.cfgId);
		out.writeUTF(data.type == null ?"" : data.type);
		out.writeLong(data.createTime);
	}

	@SuppressWarnings("unchecked")
	public static ItemData read(DataInputStream in , ItemData data) throws Exception{
		data.insId = in.readUTF();
		data.cfgId = in.readInt();
		data.type = in.readUTF();
		data.createTime = in.readLong();
		return data;
	}

}