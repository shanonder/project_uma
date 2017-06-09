package app.uma.net.socket.data;

import java.io.Serializable;
import app.uma.net.socket.data.ItemData;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:9ff1b2f52a6c4f172053a9e30951340
	 */
public class GridData implements Serializable{
	private static final long serialVersionUID = 1L;
	private int index;//序号
	private ItemData item;//唯一ID
	public int getIndex(){
		return this.index;
	}
	public void setIndex(int index){
		this.index=index;
	}
	public ItemData getItem(){
		return this.item;
	}
	public void setItem(ItemData item){
		this.item=item;
	}

}