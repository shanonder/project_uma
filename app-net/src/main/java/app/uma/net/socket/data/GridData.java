package app.uma.net.socket.data;

import app.uma.net.socket.decodes.ClientRequest;
import java.io.Serializable;
import app.uma.net.socket.data.ItemData;
	/**
	 * 此类由CodeGenerateUtil自动生成
	 * md5:494f6ee78d1370fc086573ace26b8b1a
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