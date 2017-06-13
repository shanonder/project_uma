package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
	/**
	 * 此类由CodeGenerateUtil自动生成
<<<<<<< HEAD
	 * md5:ac352a15447c1ae09498de3779e371de
=======
	 * md5:f67d8abd5c0270cf2983938580c6b3f7
>>>>>>> branch 'master' of https://github.com/shanonder/project_uma.git
	 */
public class ItemData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String insId;//唯一ID
	private int cfgId;//配置表ID
	private String type;//分类
	private double createTime;//创建时间
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