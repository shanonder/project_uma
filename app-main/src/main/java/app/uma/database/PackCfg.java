package app.uma.database;

	/**
	 * 此类由ExcelToCodeTool自动生成
	 * md5:118e973092a4297db93b5138bd8547b7
	 */
public class PackCfg{
	/**
	 * 类型
	 */
	private int type;

	/**
	 * 职业
	 */
	private int open;

	/**
	 * 资源
	 */
	private String max;

	/**
	 * 类型
	 */
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}

	/**
	 * 职业
	 */
	public int getOpen(){
		return this.open;
	}
	public void setOpen(int open){
		this.open=open;
	}

	/**
	 * 资源
	 */
	public String getMax(){
		return this.max;
	}
	public void setMax(String max){
		this.max=max;
	}


}