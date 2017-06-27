package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:b5f2ca7d3788bf78cd1b9af8e624d968
	 */
public class NpcCfg{
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