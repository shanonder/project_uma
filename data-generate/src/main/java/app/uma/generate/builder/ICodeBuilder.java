package app.uma.generate.builder;

import java.util.ArrayList;

import app.uma.generate.node.DataOptNode;
import app.uma.generate.node.MessageOptNode;

public interface ICodeBuilder {
	ArrayList<String> getOutDirs();
	
	void buildData(DataOptNode node);
	
	void buildMessage(MessageOptNode node);
	
	void buildOther();
	
}
