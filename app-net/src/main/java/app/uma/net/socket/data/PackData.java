package app.uma.net.socket.data;

import app.uma.net.socket.decodes.ClientRequest;
import java.io.Serializable;
import java.util.ArrayList;
import app.uma.net.socket.data.]GridData;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:494f6ee78d1370fc086573ace26b8b1a
	 */
public class PackData implements Serializable{
	private static final long serialVersionUID = 1L;
	private int type;//唯一ID
	private int openLength;//开启长度
	private ArrayList<]GridData> itemList;
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public int getOpenLength(){
		return this.openLength;
	}
	public void setOpenLength(int openLength){
		this.openLength=openLength;
	}
	public ArrayList<]GridData> getItemList(){
		return this.itemList;
	}
	public void setItemList(ArrayList<]GridData> itemList){
		this.itemList=itemList;
	}

}