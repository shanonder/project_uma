package app.uma.net.socket;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.uma.net.socket.services.GameService;
import app.uma.net.socket.services.LoginService;
import app.uma.net.socket.services.ModuleService;

@Configuration
public class MinaConfig {
	private final Logger logger = Logger.getLogger(MinaConfig.class);
	
	@Bean
	public LoginService loginService () throws IOException{
		LoginService ls = new LoginService(3004);
		logger.info("LoginService Start");
		return ls;
	}
	
	@Bean
	public GameService gameService(){
		GameService gs = new GameService(3005);
		logger.info("GameService Start");
		return gs;
	}
	
//	@Bean
//	public WebSocketService webSocketService(){
//		WebSocketService gs = new WebSocketService(3006);
//		logger.info("WebSocketService Start");
//		return gs;
//	}
	
	@Bean
	public ModuleService moduleService (){
		ModuleService gs = new ModuleService();
		logger.info("Modules Start");
		return gs;
	}
	
	
}
