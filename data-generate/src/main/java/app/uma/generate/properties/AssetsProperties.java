package app.uma.generate.properties;

public class AssetsProperties {
	
	private String root;
	
	public void setRoot(String root) {
		this.root = root;
	}
	public String getRoot() {
		return root;
	}
	
	public String getProtocolDataPath(){
		return root + "protocol/";
	}
	
	public String getProtocolMsgDir(){
		return root + "protocol/msg/";
	}
	
}
