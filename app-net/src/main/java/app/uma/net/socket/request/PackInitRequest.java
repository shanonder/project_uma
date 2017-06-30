package app.uma.net.socket.request;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class PackInitRequest{
	public PackInitRequest(ClientRequest request) throws Exception{
		type = request.getShort();

	}

	/**
	 * 包裹类型
	 */
	private int type;

	/**
	 * 包裹类型
	 */
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}


}