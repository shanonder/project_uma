package app.uma.net.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import app.uma.net.message.Greeting;
import app.uma.net.message.HelloMessage;

@Controller
public class GreetingController {

    @MessageMapping("/login")
    @SendTo("/respon/auth")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
