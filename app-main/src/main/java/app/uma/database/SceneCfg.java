package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:1071b8573da2fa96d63f97703411d3ec
	 */
public class SceneCfg{
	/**
	 * 配置ID
	 */
	private int id;

	/**
	 * 地图类型：1. 野外; 2. 副本 
	 */
	private int type;

	/**
	 * 资源键
	 */
	private String source;

	/**
	 * 地图名称
	 */
	private String name;

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
	 * 地图类型：1. 野外; 2. 副本 
	 */
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}

	/**
	 * 资源键
	 */
	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source=source;
	}

	/**
	 * 地图名称
	 */
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}


}