package app.uma.net.socket.datas;

import java.io.Serializable;
	/**
	 * 此类由protocol_generate_util自动生成
	 * md5:bdb4980f0cdcd230aa8ff60bbeab28c0
	 */
public class AttributesData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cfgId;
	private int value;
	public String getCfgId(){
		return this.cfgId;
	}
	public void setCfgId(String cfgId){
		this.cfgId=cfgId;
	}
	public int getValue(){
		return this.value;
	}
	public void setValue(int value){
		this.value=value;
	}

}