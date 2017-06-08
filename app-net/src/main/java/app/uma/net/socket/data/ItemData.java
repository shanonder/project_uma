package app.uma.net.socket.data;

import java.io.Serializable;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:1c505c2d0134b4cc2aec1ae1f3e8dd20
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

}