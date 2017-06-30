package app.uma.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import app.uma.generate.builder.ExcelBuilder;
import app.uma.generate.builder.ExcelVersionUtil;

@SpringBootApplication
public class ExcelGen {

	private static final Logger log = LoggerFactory.getLogger(ExcelGen.class);
	private static ApplicationContext context;
	@Bean
	public ApplicationContext appContext(){
		return context;
	}
	public static void main(String[] args) throws Exception {
		context =SpringApplication.run(ExcelGen.class, args);
		log.info("SpringStartComplete...");
		ExcelVersionUtil excelVersionUtil = context.getBean(ExcelVersionUtil.class);
		excelVersionUtil.init();
		ExcelBuilder excelBuilder = context.getBean(ExcelBuilder.class);
		excelBuilder.excute();
		excelBuilder.dispose();
		excelVersionUtil.flush();
		
	}
}
