package app.uma.net.socket.datas;

import java.io.Serializable;
import java.util.ArrayList;
import app.uma.net.socket.datas.GridData;
	/**
	 * 此类由protocol_generate_util自动生成
	 * md5:bdb4980f0cdcd230aa8ff60bbeab28c0
	 */
public class PackData implements Serializable{
	private static final long serialVersionUID = 1L;
	private int type;
	private int openLength;
	private ArrayList<GridData> itemList;
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
	public ArrayList<GridData> getItemList(){
		return this.itemList;
	}
	public void setItemList(ArrayList<GridData> itemList){
		this.itemList=itemList;
	}

}