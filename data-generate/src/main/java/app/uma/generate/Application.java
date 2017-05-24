package app.uma.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.uma.generate.builder.GenerateBuilder;
import app.uma.generate.config.GeneralBeans;

@SpringBootApplication
@EnableScheduling
public class Application {
	private static final Logger log = LoggerFactory.getLogger(GeneralBeans.class);
	private static ApplicationContext context;
	public static void main(String[] args) throws Exception {
		context =SpringApplication.run(Application.class, args);
		SpringContextUtil.setApplicationContext(context);
//		GenerateBuilder genAction = new GenerateBuilder();
//		genAction.execute();
//		genAction.dispose();
		GenerateBuilder genAction = context.getBean(GenerateBuilder.class);
		genAction.execute();
		genAction.dispose();
	}
	
	
}
