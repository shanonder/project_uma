package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:7c1b8066ed241e35583e5cad55bb3831
	 */
public class MonsterCfg{
	/**
	 * 配置ID
	 */
	private int id;

	/**
	 * npc类型
	 */
	private int type;

	/**
	 * 资源key
	 */
	private String source;

	/**
	 * 配置ID
	 */
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}

	/**
	 * npc类型
	 */
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}

	/**
	 * 资源key
	 */
	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source=source;
	}


}