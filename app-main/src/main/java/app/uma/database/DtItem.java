package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:cf87051f90c6f30436195cd426fcb391
	 */
public class DtItem{
	private String id;//ID
	private String name;//名字
	private int type;//类型
	private String source;//DESC
	private int quality;//品质
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source=source;
	}
	public int getQuality(){
		return this.quality;
	}
	public void setQuality(int quality){
		this.quality=quality;
	}

}