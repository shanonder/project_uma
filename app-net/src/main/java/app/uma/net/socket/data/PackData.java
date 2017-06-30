package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import app.uma.net.socket.data.GridData;
import app.uma.net.socket.util.ArrayUtil;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:6fbd4498441778567949fafa0192e410
	 */
public class PackData implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 唯一ID
	 */
	private int type;

	/**
	 * 开启长度
	 */
	private int openLength;

	private ArrayList<GridData> itemList;

	/**
	 * 唯一ID
	 */
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}

	/**
	 * 开启长度
	 */
	public int getOpenLength(){
		return this.openLength;
	}
	public void setOpenLength(int openLength){
		this.openLength=openLength;
	}

	public ArrayList<GridData> getItemList(){
		return this.itemList;
	}
	public void setItemList(ArrayList<GridData> itemList){
		this.itemList=itemList;
	}


	public static void write(DataOutputStream out , PackData data) throws Exception{
		out.writeShort(data.type);
		out.writeShort(data.openLength);
		ArrayUtil.write( out , data.itemList);
	}

	@SuppressWarnings("unchecked")
	public static PackData read(DataInputStream in , PackData data) throws Exception{
		if(data == null){
			data = new PackData();
		}
		data.type = in.readShort();
		data.openLength = in.readShort();
		data.itemList = ArrayUtil.read(in);
		return data;
	}

}