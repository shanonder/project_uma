package app.uma.net.socket.data;

import java.io.Serializable;
import java.util.ArrayList;
import app.uma.net.socket.data.AttributesData;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:1c505c2d0134b4cc2aec1ae1f3e8dd20
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