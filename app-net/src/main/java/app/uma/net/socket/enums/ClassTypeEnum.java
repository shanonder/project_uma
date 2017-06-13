package app.uma.net.socket.enums;

public enum ClassTypeEnum {
	t_byte(-1,"java.lang.Byte")
	, t_boolean(-2,"java.lang.Boolean")
	, t_short(-3,"java.lang.Short")
	, t_int(-4,"java.lang.Integer")
	, t_double(-5,"java.lang.Long")
	, t_long(-6,"java.lang.Long")
	, t_string(-7,"java.lang.String")
	, t_array(-8,"java.lang.ArrayList");

	private int type;
	private String refClass;
	
	private ClassTypeEnum(int type , String refClass){
		this.type = type;
		this.refClass = refClass;
	}

	public String getRefClass(){
		return this.refClass;
	}

	public int getType(){
		return this.type;
	}
}
