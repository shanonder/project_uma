package app.uma.net.socket.datas;

import java.io.Serializable;
import java.util.ArrayList;
import app.uma.net.socket.datas.AttributesData;
	/**
	 * 此类由protocol_generate_util自动生成
	 * md5:bdb4980f0cdcd230aa8ff60bbeab28c0
	 */
public class RoleData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String insId;
	private String cfgId;
	private String name;
	private int level;
	private int exp;
	private ArrayList<AttributesData> attributes;
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
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getLevel(){
		return this.level;
	}
	public void setLevel(int level){
		this.level=level;
	}
	public int getExp(){
		return this.exp;
	}
	public void setExp(int exp){
		this.exp=exp;
	}
	public ArrayList<AttributesData> getAttributes(){
		return this.attributes;
	}
	public void setAttributes(ArrayList<AttributesData> attributes){
		this.attributes=attributes;
	}

}