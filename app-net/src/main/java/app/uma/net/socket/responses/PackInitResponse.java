package app.uma.net.socket.responses;

import app.uma.net.socket.decodes.ServerResponse;
import java.util.ArrayList;
import app.uma.net.socket.datas.ItemData;
/**
 * 此类由protocol_generate_util自动生成
 */
public class PackInitResponse  extends ServerResponse{
	public PackInitResponse(int status , int type, int openLength, ArrayList<ItemData> itemList) throws Exception{
		super(0x40001 , status);
		output.writeShort(type);
		output.writeShort(openLength);
		output.writeAMFObject(itemList == null ? new ArrayList<>() : itemList);

	}


}