package app.uma.generate.properties;

import java.util.ArrayList;

public class CodeProperties {
	private String path;

	private String pack;

	private String lang;
	
	private String packRequest;
	
	private String packResponse;
	
	private String packData;

	public String getPackRequest() {
		return packRequest;
	}

	public void setPackRequest(String packRequest) {
		this.packRequest = packRequest;
	}

	public String getPackResponse() {
		return packResponse;
	}

	public void setPackResponse(String packResponse) {
		this.packResponse = packResponse;
	}

	public String getPackData() {
		return packData;
	}

	public void setPackData(String packData) {
		this.packData = packData;
	}

	private ArrayList<String> requestImport;

	private ArrayList<String> responseImport;

	private ArrayList<String> dataImport;

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public ArrayList<String> getRequestImport() {
		return requestImport;
	}

	public void setRequestImport(ArrayList<String> requestImport) {
		this.requestImport = requestImport;
	}

	public ArrayList<String> getResponseImport() {
		return responseImport;
	}

	public void setResponseImport(ArrayList<String> responseImport) {
		this.responseImport = responseImport;
	}

	public ArrayList<String> getDataImport() {
		return dataImport;
	}

	public void setDataImport(ArrayList<String> dataImport) {
		this.dataImport = dataImport;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

}
