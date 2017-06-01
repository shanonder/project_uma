package app.uma.net.socket.data;

import java.io.Serializable;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:5096a07b246f0a976f543a8c5aef3a95
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

}