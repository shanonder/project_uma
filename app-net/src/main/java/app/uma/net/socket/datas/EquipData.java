package app.uma.net.socket.datas;

import java.io.Serializable;
import java.util.ArrayList;
import app.uma.net.socket.datas.AttributesData;
	/**
	 * 此类由protocol_generate_util自动生成
	 * md5:bdb4980f0cdcd230aa8ff60bbeab28c0
	 */
public class EquipData extends ItemData implements Serializable{
	private static final long serialVersionUID = 1L;
	private int strenthLv;
	private ArrayList<AttributesData> attributes;
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