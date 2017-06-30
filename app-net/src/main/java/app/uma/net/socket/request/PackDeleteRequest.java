package app.uma.net.socket.request;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class PackDeleteRequest{
	public PackDeleteRequest(ClientRequest request) throws Exception{
		type = request.getShort();
		index = request.getShort();
		insId = request.getString();

	}

	/**
	 * 包裹类型
	 */
	private int type;

	/**
	 * 包裹索引
	 */
	private int index;

	/**
	 * 包裹物品唯一ID
	 */
	private String insId;

	/**
	 * 包裹类型
	 */
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}

	/**
	 * 包裹索引
	 */
	public int getIndex(){
		return this.index;
	}
	public void setIndex(int index){
		this.index=index;
	}

	/**
	 * 包裹物品唯一ID
	 */
	public String getInsId(){
		return this.insId;
	}
	public void setInsId(String insId){
		this.insId=insId;
	}


}