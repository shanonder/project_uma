/**
 *
 * author: shanonder
 * date: May 1, 2017 1:28:53 AM
 *
 */
package app.uma.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.uma.web.resp.LoginResponse;

@Controller
public class AuthController {
    @GetMapping("/auth/login")
    public LoginResponse loginResponse(@RequestParam(required=true) String username,@RequestParam(required=true) String password) {
        System.out.println("==== loginCheck ====");
        return new LoginResponse();
    }
}
