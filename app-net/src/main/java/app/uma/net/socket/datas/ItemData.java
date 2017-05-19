package app.uma.net.socket.datas;

import java.io.Serializable;
	/**
	 * 此类由protocol_generate_util自动生成
	 * md5:bdb4980f0cdcd230aa8ff60bbeab28c0
	 */
public class ItemData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String insId;
	private String cfgId;
	private int type;
	private double createTime;
	public String getInsId(){
		return this.insId;
	}
	public void setInsId(String insId){
		this.insId=insId;
	}
	public String getCfgId(){
		return this.cfgId;
	}
	public void setCfgId(String cfgId){
		this.cfgId=cfgId;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public double getCreateTime(){
		return this.createTime;
	}
	public void setCreateTime(double createTime){
		this.createTime=createTime;
	}

}