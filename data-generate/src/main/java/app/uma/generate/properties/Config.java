package app.uma.generate.properties;

public class Config {
	
	
	private boolean generateAll;

	private String appName;
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
}
