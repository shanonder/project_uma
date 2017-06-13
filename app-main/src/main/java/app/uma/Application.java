package app.uma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import app.uma.net.socket.services.GameService;
import app.uma.net.socket.services.LoginService;

@SpringBootApplication
public class Application {
	

	public static void main(String[] args) throws Exception{
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		context.getBean(GameService.class).start();
		context.getBean(LoginService.class).start();
	}
}
