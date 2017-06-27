package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class HeapResponse  extends ServerResponse{

	/**
	 * @struct int 状态  200 正常
	 * @time double 当前时间
	 */
	public HeapResponse(int status , double time) throws Exception{
		super(0x20000 , status);
		output.writeDouble(time);

	}


}