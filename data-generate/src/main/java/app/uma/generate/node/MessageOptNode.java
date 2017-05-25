package app.uma.generate.node;

import java.util.ArrayList;

public class MessageOptNode {
	private String name;
	private String desc;
	private String md5;
	private String cmd;
	public ArrayList<CellVO> c2sCells;
	public ArrayList<CellVO> s2cCells;
	
	public MessageOptNode(){
		c2sCells = new ArrayList<>();
		s2cCells = new ArrayList<>();
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
}
