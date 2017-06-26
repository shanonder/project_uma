package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:52b142ff2093e834a9bc65d4fb64a91c
	 */
public class RoleCfg{
	private String id;//编号
	private int profession;//职业
	private String source;//资源
	private int gender;//性别
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	public int getProfession(){
		return this.profession;
	}
	public void setProfession(int profession){
		this.profession=profession;
	}
	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source=source;
	}
	public int getGender(){
		return this.gender;
	}
	public void setGender(int gender){
		this.gender=gender;
	}

}