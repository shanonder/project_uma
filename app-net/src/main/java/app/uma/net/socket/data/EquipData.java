package app.uma.net.socket.data;

import app.uma.net.socket.decodes.ClientRequest;
import java.io.Serializable;
import java.util.ArrayList;
import app.uma.net.socket.data.]AttributesData>;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:494f6ee78d1370fc086573ace26b8b1a
	 */
public class EquipData extends ItemData implements Serializable{
	private static final long serialVersionUID = 1L;
	private int strenthLv;//唯一ID
	private ArrayList<]AttributesData>> attributes;//属性
	public int getStrenthLv(){
		return this.strenthLv;
	}
	public void setStrenthLv(int strenthLv){
		this.strenthLv=strenthLv;
	}
	public ArrayList<]AttributesData>> getAttributes(){
		return this.attributes;
	}
	public void setAttributes(ArrayList<]AttributesData>> attributes){
		this.attributes=attributes;
	}

}