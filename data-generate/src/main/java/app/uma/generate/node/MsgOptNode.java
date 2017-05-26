package app.uma.generate.node;

import java.util.ArrayList;

public class MsgOptNode {
	private String name;
	private String desc;
	private String md5;
	private String cmd;
	private String type;
	public ArrayList<CellVO> cells;
	
	private Boolean isC2S;
	
	public Boolean getIsC2S() {
		return isC2S;
	}

	public void setIsC2S(Boolean isC2S) {
		this.isC2S = isC2S;
	}

	public Boolean getIsS2C() {
		return isS2C;
	}

	public void setIsS2C(Boolean isS2C) {
		this.isS2C = isS2C;
	}

	private Boolean isS2C;
	
	public MsgOptNode(){
		cells = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
