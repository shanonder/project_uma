package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
import app.uma.net.socket.data.ItemData;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class PackAddResponse  extends ServerResponse{

	/**
	 * @struct int 状态  200 正常
	 * @type short 包裹类型
	 * @index short 包裹索引
	 * @item ItemData 物品
	 */
	public PackAddResponse(int status , int type, int index, ItemData item) throws Exception{
		super(0x40005 , status);
		output.writeShort(type);
		output.writeShort(index);
		output.writeData(item == null ? new Object() : item);

	}


}