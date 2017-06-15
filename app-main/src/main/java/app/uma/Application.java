package app.uma;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import app.uma.csv.CsvUtil;
import app.uma.database.DtPack;
import app.uma.net.socket.services.GameService;
import app.uma.net.socket.services.LoginService;

@SpringBootApplication
public class Application {
	
	public static ApplicationContext context;
	public static void main(String[] args) throws Exception{
		ArrayList<DtPack> pack = (ArrayList<DtPack>) CsvUtil.getCsv("pack.dat",DtPack.class);
		context = SpringApplication.run(Application.class, args);
		context.getBean(GameService.class).start();
		context.getBean(LoginService.class).start();
	}
}
