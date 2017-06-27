package app.uma.net.socket.response;

import app.uma.net.socket.decodes.ServerResponse;
import java.util.ArrayList;
import app.uma.net.socket.data.PackData;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class PackInitResponse  extends ServerResponse{

	/**
	 * @struct int 状态  200 正常
	 * @packs []PackData 
	 */
	public PackInitResponse(int status , ArrayList<PackData> packs) throws Exception{
		super(0x40001 , status);
		output.writeArrayList(packs);

	}


}