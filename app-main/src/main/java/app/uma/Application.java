package app.uma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import app.uma.net.socket.services.GameService;
import app.uma.net.socket.services.LoginService;
import app.uma.net.socket.services.ModuleService;

@SpringBootApplication
public class Application {
	
//	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static ApplicationContext context;
	public static void main(String[] args) throws Exception{
		context = SpringApplication.run(Application.class, args);
		context.getBean(GameService.class).start();
		context.getBean(LoginService.class).start();
//		context.getBean(WebSocketService.class).start();
		context.getBean(ModuleService.class).start();
	}
}
