package app.uma.generate.properties;

public class AssetsProperties {
	
	private String root;
	
	public void setRoot(String root) {
		this.root = root;
	}
	public String getRoot() {
		return root;
	}
	
	public String getProtocolXlsxPath(){
		return root + "protocol/";
	}
	
}
