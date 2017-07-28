package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:3900224f6a98db0644f517aeedffd7b1
	 */
public class TaskCfg{
	/**
	 * 配置ID
	 */
	private int id;

	/**
	 * 任务名称
	 */
	private String name;

	/**
	 * 完成参数
	 */
	private String params;

	/**
	 * 奖励
	 */
	private String reward;

	/**
	 * 完成后的后续任务
	 */
	private int next;

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
	 * 任务名称
	 */
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}

	/**
	 * 完成参数
	 */
	public String getParams(){
		return this.params;
	}
	public void setParams(String params){
		this.params=params;
	}

	/**
	 * 奖励
	 */
	public String getReward(){
		return this.reward;
	}
	public void setReward(String reward){
		this.reward=reward;
	}

	/**
	 * 完成后的后续任务
	 */
	public int getNext(){
		return this.next;
	}
	public void setNext(int next){
		this.next=next;
	}


}