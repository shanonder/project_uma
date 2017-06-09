package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class HeapResponse  extends ServerResponse{
	public HeapResponse(int status , long time) throws Exception{
		super(0x20000 , status);
		output.writeLong(time);

	}


}