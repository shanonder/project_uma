package app.uma.generate.node;

import java.util.ArrayList;


public class DataOptNode {
	private int dataId;
	private String name;
	private String desc;
	private String md5;
	private String parent;
	public ArrayList<CellVO> cells;
	
	public int getDataId() {
		return dataId;
	}

	public void setDataId(int dataId) {
		this.dataId = dataId;
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}


	
	public DataOptNode() {
		cells = new ArrayList<>();
	}
}
