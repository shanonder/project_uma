package app.uma.net.socket.request;

import app.uma.net.socket.decodes.ClientRequest;
/**
 * 此类由CodeGenerateUtil自动生成
 */
public class PackMoveRequest{
	public PackMoveRequest(ClientRequest request) throws Exception{
		type0 = request.getShort();
		index0 = request.getShort();
		insId0 = request.getString();
		type1 = request.getShort();
		index1 = request.getShort();
		insId1 = request.getString();

	}

	private int type0;//移动源包裹类型
	private int index0;//移动源包裹索引
	private String insId0;//移动源包裹物品唯一ID
	private int type1;//移动目标包裹类型
	private int index1;//移动目标包裹索引
	private String insId1;//移动目标包裹物品唯一ID
	public int getType0(){
		return this.type0;
	}
	public void setType0(int type0){
		this.type0=type0;
	}
	public int getIndex0(){
		return this.index0;
	}
	public void setIndex0(int index0){
		this.index0=index0;
	}
	public String getInsId0(){
		return this.insId0;
	}
	public void setInsId0(String insId0){
		this.insId0=insId0;
	}
	public int getType1(){
		return this.type1;
	}
	public void setType1(int type1){
		this.type1=type1;
	}
	public int getIndex1(){
		return this.index1;
	}
	public void setIndex1(int index1){
		this.index1=index1;
	}
	public String getInsId1(){
		return this.insId1;
	}
	public void setInsId1(String insId1){
		this.insId1=insId1;
	}

}