package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class PackDeleteResponse  extends ServerResponse{

	/**
	 * @struct int 状态  200 正常
	 * @type short 包裹类型
	 * @index short 包裹索引
	 * @insId string 包裹物品唯一ID(检查用)
	 */
	public PackDeleteResponse(int status , int type, int index, String insId) throws Exception{
		super(0x40003 , status);
		output.writeShort(type);
		output.writeShort(index);
		output.writeUTF(insId == null ? "" : insId);

	}


}