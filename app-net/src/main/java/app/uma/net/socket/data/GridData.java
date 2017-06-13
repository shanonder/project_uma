package app.uma.net.socket.data;

import java.io.Serializable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import app.uma.net.socket.data.ItemData;
import app.uma.net.socket.util.DataUtil;
	/**
	 * 此类由CodeGenerateUtil自动生成
<<<<<<< HEAD
	 * md5:ac352a15447c1ae09498de3779e371de
=======
	 * md5:f67d8abd5c0270cf2983938580c6b3f7
>>>>>>> branch 'master' of https://github.com/shanonder/project_uma.git
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

	public static void write(DataOutputStream out , GridData data) throws Exception{
		out.writeShort(data.index);
		DataUtil.write( out , data.item);
	}

	@SuppressWarnings("unchecked")
	public static GridData read(DataInputStream in , GridData data) throws Exception{
		if(data == null){
			data = new GridData();
		}
		data.index = in.readShort();
		data.item = DataUtil.read(in);
		return data;
	}

}