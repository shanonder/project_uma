package app.uma.generate.vo;

import java.util.ArrayList;

public class MessageOptNode {
	public String name;
	public String desc;
	public String md5;
	public String cmd;
	public ArrayList<CellVO> c2sCells;
	public ArrayList<CellVO> s2cCells;
	
	public MessageOptNode(){
		c2sCells = new ArrayList<>();
		s2cCells = new ArrayList<>();
	}
}
