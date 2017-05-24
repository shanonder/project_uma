package app.uma.generate.properties;

import java.util.ArrayList;

public class CodeProperties {
	private String path;

	private String packurl;

	private String lang;

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

	public String getPackurl() {
		return packurl;
	}

	public void setPackurl(String packurl) {
		this.packurl = packurl;
	}

}
