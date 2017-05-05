package app.uma.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import app.uma.dao.entity.User;
import app.uma.dao.repository.UserRepository;
import app.uma.message.Acount;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired 
	private UserRepository userRepository;
	
	
	
	@RequestMapping(method = RequestMethod.POST,path="/loginPost")
	public @ResponseBody Acount login(@RequestParam String username,@RequestParam String password){
		Acount acount = new Acount();
		
		User user = userRepository.findByUsername(username);
		if(user == null){
			acount.state = 102;
			acount.username = username;
		}else{
			acount.state = 200;
			acount.username = username;
			acount.token = "123123123";
			acount.uid = user.getId();
		}
		
		return acount;
	}
	
	@PostMapping(path="/registPost") // Map ONLY GET Requests
	public @ResponseBody Acount regist(@RequestParam String username,@RequestParam String password){
		Acount acount = new Acount();
		User user = userRepository.findByUsername(username);
		if(user == null){
			acount.state = 200;
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			userRepository.save(user);
		}else{
			acount.state = 103;
		}
		return acount;
	}


}
