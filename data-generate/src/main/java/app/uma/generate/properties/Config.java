package app.uma.generate.properties;

public class Config {
	
	
	private boolean generateAll;

	private String appName;
	private String version;
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public boolean isGenerateAll() {
		return generateAll;
	}

	public void setGenerateAll(boolean generateAll) {
		this.generateAll = generateAll;
	}

	public String getVersion() {
		// TODO Auto-generated method stub
		return version;
	} 
}
