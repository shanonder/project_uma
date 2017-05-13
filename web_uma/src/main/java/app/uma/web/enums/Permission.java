/**
 *
 * author: shanonder
 * date: 2017年5月13日 下午5:43:42
 *
 */
package app.uma.web.enums;

public enum Permission {
	
	Admin("admin", 1), Engin("engin", 2), User("user", 3), Guest("Guest", 4);  
	
	private String name;
	private int index;
	Permission(String name, int index) {  
		this.name = name;  
		this.index = index;  
	} 
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}
	
}
