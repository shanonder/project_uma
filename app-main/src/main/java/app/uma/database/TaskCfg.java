package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:d993f23c7cc90c7dc58d97a7c006cdcd
	 */
public class TaskCfg{
	/**
	 * 配置ID
	 */
	private int id;

	/**
	 * 下一项
	 */
	private int next;

	/**
	 * 任务名称
	 */
	private String name;

	/**
	 * 任务类型
	 */
	private int type;

	/**
	 * 参数
	 */
	private String params;

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
	 * 下一项
	 */
	public int getNext(){
		return this.next;
	}
	public void setNext(int next){
		this.next=next;
	}

	/**
	 * 任务名称
	 */
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}

	/**
	 * 任务类型
	 */
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}

	/**
	 * 参数
	 */
	public String getParams(){
		return this.params;
	}
	public void setParams(String params){
		this.params=params;
	}


}