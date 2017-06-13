package app.uma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.uma.ModuleFacade;
import app.uma.net.socket.interfaces.IModuleFacade;

@Configuration
public class BeansConfig {
	
	@Bean
	public IModuleFacade modelfacade(){
		return new ModuleFacade();
	}
}
