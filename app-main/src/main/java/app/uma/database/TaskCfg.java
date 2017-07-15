package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:89a6da506b39596d2455db215821e268
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
	 * 任务类型
	 */
	private int type;

	/**
	 * 任务组
	 */
	private int groupId;

	/**
	 * 成功奖励
	 */
	private String sucReward;

	/**
	 * 完成后的后续任务
	 */
	private int sucNext;

	/**
	 * 成功参数
	 */
	private String sucParams;

	/**
	 * 失败奖励
	 */
	private String failReward;

	/**
	 * 失败参数
	 */
	private String failParams;

	/**
	 * 失败的后续任务
	 */
	private int failNext;

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
	 * 任务类型
	 */
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}

	/**
	 * 任务组
	 */
	public int getGroupId(){
		return this.groupId;
	}
	public void setGroupId(int groupId){
		this.groupId=groupId;
	}

	/**
	 * 成功奖励
	 */
	public String getSucReward(){
		return this.sucReward;
	}
	public void setSucReward(String sucReward){
		this.sucReward=sucReward;
	}

	/**
	 * 完成后的后续任务
	 */
	public int getSucNext(){
		return this.sucNext;
	}
	public void setSucNext(int sucNext){
		this.sucNext=sucNext;
	}

	/**
	 * 成功参数
	 */
	public String getSucParams(){
		return this.sucParams;
	}
	public void setSucParams(String sucParams){
		this.sucParams=sucParams;
	}

	/**
	 * 失败奖励
	 */
	public String getFailReward(){
		return this.failReward;
	}
	public void setFailReward(String failReward){
		this.failReward=failReward;
	}

	/**
	 * 失败参数
	 */
	public String getFailParams(){
		return this.failParams;
	}
	public void setFailParams(String failParams){
		this.failParams=failParams;
	}

	/**
	 * 失败的后续任务
	 */
	public int getFailNext(){
		return this.failNext;
	}
	public void setFailNext(int failNext){
		this.failNext=failNext;
	}


}