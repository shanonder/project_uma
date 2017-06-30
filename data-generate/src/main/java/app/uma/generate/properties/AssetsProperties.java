package app.uma.generate.properties;

public class AssetsProperties {
	
	private String root;
	private String sXlsOutPath;
	private String cXlsOutPath;
	
	public String getsXlsOutPath() {
		return sXlsOutPath;
	}
	public void setsXlsOutPath(String sXlsOutPath) {
		this.sXlsOutPath = sXlsOutPath;
	}
	public String getcXlsOutPath() {
		return cXlsOutPath;
	}
	public void setcXlsOutPath(String cXlsOutPath) {
		this.cXlsOutPath = cXlsOutPath;
	}
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
	
	public String getExcelDataPath(){
		return root + "xlsx/";
	}
	
	public String getExcelVersionUrl(){
		return root + "md5.csv";
	}
}
