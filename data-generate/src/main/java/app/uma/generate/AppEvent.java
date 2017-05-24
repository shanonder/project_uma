package app.uma.generate;

import org.springframework.context.ApplicationEvent;

public class AppEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type;
	public AppEvent(Object source,String type) {
		super(source);
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
