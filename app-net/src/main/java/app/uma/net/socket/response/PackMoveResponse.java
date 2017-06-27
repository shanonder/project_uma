package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class PackMoveResponse  extends ServerResponse{

	/**
	 * @struct int 状态  200 正常
	 * @type0 short 移动源包裹类型
	 * @index0 short 移动源包裹索引
	 * @insId0 string 移动源包裹物品唯一ID
	 * @type1 short 移动目标包裹类型
	 * @index1 short 移动目标包裹索引
	 * @insId1 string 移动目标包裹物品唯一ID
	 */
	public PackMoveResponse(int status , int type0, int index0, String insId0, int type1, int index1, String insId1) throws Exception{
		super(0x40002 , status);
		output.writeShort(type0);
		output.writeShort(index0);
		output.writeUTF(insId0 == null ? "" : insId0);
		output.writeShort(type1);
		output.writeShort(index1);
		output.writeUTF(insId1 == null ? "" : insId1);

	}


}