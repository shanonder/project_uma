package app.uma.net.socket.responses;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由protocol_generate_util自动生成
 */
public class HeapResponse  extends ServerResponse{
	public HeapResponse(int status , double time) throws Exception{
		super(0x10000 , status);
		output.writeDouble(time);

	}


}