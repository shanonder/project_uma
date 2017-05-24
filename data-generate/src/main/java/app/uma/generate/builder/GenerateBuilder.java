package app.uma.generate.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import app.uma.generate.SpringContextUtil;
import app.uma.generate.properties.Config;

@Component
public class GenerateBuilder {

	@Autowired
	private Config config;
	
	@Autowired
	private ApplicationContext context;
	public void execute() throws Exception{
		
//		context = SpringContextUtil.getApplicationContext();
		GenerateVersion version = context.getBean(GenerateVersion.class);
		config.getAppName();
		version.init();
		new DataExecute().execute();
		
		version.flush();
	}


	public void dispose() {
		
	}
}
