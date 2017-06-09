package app.uma.net.socket.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import app.uma.net.socket.util.ArrayUtil;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:9ff1b2f52a6c4f172053a9e30951340
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
	
	public static void write(DataOutputStream out,EquipData data) throws Exception{
		if(data == null){
			out.writeBoolean(false);
		}else{
			out.writeBoolean(true);
		}
		out.writeInt(data.strenthLv);
		ArrayUtil.write(out, data.attributes);
	}
	
	@SuppressWarnings("unchecked")
	public static EquipData read(DataInputStream in , EquipData data) throws Exception{
		boolean notNull = in.readBoolean();
		if(notNull == false){
			return null;
		}
		if(data == null){
			data = new EquipData();
		}
		data.strenthLv = in.readInt();
		data.attributes =  ArrayUtil.read(in);
		return data;
	}

}