package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class PackMoveResponse  extends ServerResponse{
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