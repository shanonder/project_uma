package app.uma.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.uma.ModuleFacade;
import app.uma.csv.CsvUtil;
import app.uma.net.socket.interfaces.IModuleFacade;

@Configuration
public class BeansConfig {
	
	@Bean
	public IModuleFacade modelfacade(){
		return new ModuleFacade();
	}
	
	@Bean
	public CsvUtil csvUtil(){
		return new CsvUtil();
	}
	
	@Bean
	@ConfigurationProperties("app.config.dir")
	public AppDirProperties assetsProperties(){
		return new AppDirProperties();
	}
}
