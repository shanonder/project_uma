package app.uma.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login( Model model) {
        model.addAttribute("type", "login");
        return "login";
    }
    
    @RequestMapping("/forgot")
    public String forgot(Model model) {
        model.addAttribute("type", "forgot");
        return "login";
    }
    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("type", "signup");
        return "login";
    }
}
