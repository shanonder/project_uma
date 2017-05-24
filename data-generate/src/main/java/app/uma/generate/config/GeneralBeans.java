package app.uma.generate.config;

import java.io.IOException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.uma.generate.builder.GenerateVersion;
import app.uma.generate.properties.AssetsProperties;
import app.uma.generate.properties.CodeProperties;
import app.uma.generate.properties.Config;


@Configuration
public class GeneralBeans {
	
	public static final String JAVA_PROPERTIES = "JavaProperties";
	public static final String AS_PROPERTIES = "AsProperties";
	@Bean
	@ConfigurationProperties("config")
	public Config config(){
		return new Config();
	}
	
	@Bean
	@ConfigurationProperties("assets.data")
	public AssetsProperties assetsProperties(){
		return new AssetsProperties();
	}
	
	@Bean(JAVA_PROPERTIES)
	@ConfigurationProperties("code.java")
	public CodeProperties cjProperties(){
		return new CodeProperties();
	}
	
	@Bean(AS_PROPERTIES)
	@ConfigurationProperties("code.as")
	public CodeProperties asProperties(){
		return new CodeProperties();
	}
	
//	@Bean
//	public GenerateVersion version() throws IOException{
//		GenerateVersion v = new GenerateVersion();
////		v.init();
//		return v;
//	}
	
	public GeneralBeans() {
		
	}
	


	
}
