package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:3695163e297c986917a7488749f40fbf
	 */
public class RoleCfg{
	/**
	 * 编号
	 */
	private int id;

	/**
	 * 职业
	 */
	private int profession;

	/**
	 * 资源
	 */
	private String source;

	/**
	 * 性别
	 */
	private int gender;

	/**
	 * 编号
	 */
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}

	/**
	 * 职业
	 */
	public int getProfession(){
		return this.profession;
	}
	public void setProfession(int profession){
		this.profession=profession;
	}

	/**
	 * 资源
	 */
	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source=source;
	}

	/**
	 * 性别
	 */
	public int getGender(){
		return this.gender;
	}
	public void setGender(int gender){
		this.gender=gender;
	}


}