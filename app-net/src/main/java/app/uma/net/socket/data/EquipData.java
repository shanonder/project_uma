package app.uma.net.socket.data;

import java.io.Serializable;
import java.util.ArrayList;
import app.uma.net.socket.data.AttributesData;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:5096a07b246f0a976f543a8c5aef3a95
	 */
public class EquipData extends ItemData implements Serializable{
	private static final long serialVersionUID = 1L;
	private int strenthLv;//唯一ID
	private ArrayList<AttributesData> attributes;//属性
	public int getStrenthLv(){
		return this.strenthLv;
	}
	public void setStrenthLv(int strenthLv){
		this.strenthLv=strenthLv;
	}
	public ArrayList<AttributesData> getAttributes(){
		return this.attributes;
	}
	public void setAttributes(ArrayList<AttributesData> attributes){
		this.attributes=attributes;
	}

}