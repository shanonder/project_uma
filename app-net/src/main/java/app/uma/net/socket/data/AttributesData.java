package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:9ff1b2f52a6c4f172053a9e30951340
	 */
public class AttributesData implements Serializable{
	private static final long serialVersionUID = 1L;
	private int cfgId;//配置表ID
	private long value;
	public int getCfgId(){
		return this.cfgId;
	}
	public void setCfgId(int cfgId){
		this.cfgId=cfgId;
	}
	public long getValue(){
		return this.value;
	}
	public void setValue(long value){
		this.value=value;
	}

	public static void write(DataOutputStream out , AttributesData data) throws Exception{
		out.writeInt(data.cfgId);
		out.writeLong(data.value);
	}

	@SuppressWarnings("unchecked")
	public static AttributesData read(DataInputStream in , AttributesData data) throws Exception{
		data.cfgId = in.readInt();
		data.value = in.readLong();
		return data;
	}

}