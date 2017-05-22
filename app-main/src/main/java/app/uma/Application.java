package app.uma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	

	public static void main(String[] args) throws Exception{
		SpringApplication.run(Application.class, args);
		
//		SqlManager.getInstance().init();
//		logger.info("sql start complete...");
//		NetManager.getInstance().init();
//		logger.info("sql start complete...");
//		LoginService loginService = new LoginService(3004);
//		GameService gameService = new GameService(3005);
//		loginService.start();
//		gameService.start();
	}
}
