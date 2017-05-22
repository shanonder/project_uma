package app.uma.generate.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component  
@ConfigurationProperties(prefix="java")
public class CodeJavaProperties {
	private String path;
	
	private String packurl;
	
	private String packResponse;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPackurl() {
		return packurl;
	}

	public void setPackurl(String packurl) {
		this.packurl = packurl;
	}

	public String getPackResponse() {
		return packResponse;
	}

	public void setPackResponse(String packResponse) {
		this.packResponse = packResponse;
	}

	public String getPackRequest() {
		return packRequest;
	}

	public void setPackRequest(String packRequest) {
		this.packRequest = packRequest;
	}

	private String packRequest;
	
}
