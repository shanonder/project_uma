package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
import app.uma.net.socket.data.ItemData;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class PackAddResponse  extends ServerResponse{
	public PackAddResponse(int status , int type, int index, ItemData item) throws Exception{
		super(0x40005 , status);
		output.writeShort(type);
		output.writeShort(index);
		output.writeAMFObject(item == null ? new Object() : item);

	}


}