package app.uma.net.socket.data;

import app.uma.net.socket.decodes.ClientRequest;
import java.io.Serializable;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:494f6ee78d1370fc086573ace26b8b1a
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