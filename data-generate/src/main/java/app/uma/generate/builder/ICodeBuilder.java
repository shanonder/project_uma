package app.uma.generate.builder;

import java.util.ArrayList;

import app.uma.generate.vo.DataOptNode;
import app.uma.generate.vo.MessageOptNode;

public interface ICodeBuilder {
	ArrayList<String> getOutDirs();
	
	void buildData(DataOptNode node);
	
	void buildMessage(MessageOptNode node);
	
	void buildOther();
	
}
