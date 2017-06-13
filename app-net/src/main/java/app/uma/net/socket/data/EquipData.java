package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import app.uma.net.socket.data.AttributesData;
import app.uma.net.socket.util.ArrayUtil;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:ac352a15447c1ae09498de3779e371de
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

	public static void write(DataOutputStream out , EquipData data) throws Exception{
		ItemData.write( out , data );
		out.writeInt(data.strenthLv);
		ArrayUtil.write( out , data.attributes);
	}

	@SuppressWarnings("unchecked")
	public static EquipData read(DataInputStream in , EquipData data) throws Exception{
		ItemData.read( in , data );
		data.strenthLv = in.readInt();
		data.attributes = ArrayUtil.read(in);
		return data;
	}

}