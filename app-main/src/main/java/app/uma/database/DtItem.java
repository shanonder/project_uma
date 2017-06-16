package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:938af68134f347ba72866e10711662dc
	 */
public class DtItem{
	private int id;//ID
	private String name;//名字
	private int type;//类型
	private String source;//DESC
	private int quality;//品质
	public int getId(){
		return this.id;
	}
	public void setId(int id){
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