package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class Heap  extends ServerResponse{
	public Heap(int status , long time) throws Exception{
		super(0x20000 , status);
		output.writeAMFObject(time == null ? new Object() : time);

	}


}