package app.uma.generate.vo;

import java.util.ArrayList;

public class DataOptCell {
	public int dataId;
	public String name;
	public String desc;
	public String md5;
	public String parent;
	public ArrayList<CellVO> cells;
	
	public DataOptCell(){
		cells = new ArrayList<>();
//		dataId = Config.counter.getAndIncrement();
	}

//	public void operate() {
//		new JavaDataBuilder(name,parent,cells,desc,md5);
//		new AsDataCodeBuilder(name,parent, cells,desc, md5);
//	}
	
}
