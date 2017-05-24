package app.uma.generate.builder;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import app.uma.generate.SpringContextUtil;


@Component
public class GenerateBuilder {
	
	
	public void execute() throws Exception{
		
		ApplicationContext context = SpringContextUtil.getApplicationContext();
		GenerateVersion version = context.getBean(GenerateVersion.class);
		version.init();
		data
		
		version.flush();
	}


	public void dispose() {
		
	}
}
