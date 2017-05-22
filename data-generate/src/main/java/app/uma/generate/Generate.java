package app.uma.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.uma.generate.properties.CodeJavaProperties;


@Configuration
public class Generate {
	private static final Logger log = LoggerFactory.getLogger(Generate.class);

//	@Resource
//	private CodeJavaProperties cjProperties;
	public Generate() {
		
	}
	
	
	@Bean
	@ConfigurationProperties("java")
	public CodeJavaProperties cjProperties(){
		return new CodeJavaProperties();
	}
	
}
