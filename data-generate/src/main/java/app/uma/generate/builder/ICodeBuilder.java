package app.uma.generate.builder;

import java.util.ArrayList;

import app.uma.generate.node.DataOptNode;
import app.uma.generate.node.MsgOptNode;

public interface ICodeBuilder {
	ArrayList<String> getOutDirs();
	
	void buildData(DataOptNode node);
	
	void buildMessage(MsgOptNode node);
	
	void buildOther();
	
}
