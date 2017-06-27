package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:6fbd4498441778567949fafa0192e410
	 */
public class AttributesData implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 配置表ID
	 */
	private int cfgId;

	private double value;

	/**
	 * 配置表ID
	 */
	public int getCfgId(){
		return this.cfgId;
	}
	public void setCfgId(int cfgId){
		this.cfgId=cfgId;
	}

	public double getValue(){
		return this.value;
	}
	public void setValue(double value){
		this.value=value;
	}


	public static void write(DataOutputStream out , AttributesData data) throws Exception{
		out.writeInt(data.cfgId);
		out.writeDouble(data.value);
	}

	@SuppressWarnings("unchecked")
	public static AttributesData read(DataInputStream in , AttributesData data) throws Exception{
		if(data == null){
			data = new AttributesData();
		}
		data.cfgId = in.readInt();
		data.value = in.readDouble();
		return data;
	}

}