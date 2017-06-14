package app.uma.model;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.dao.entity.User;
import app.uma.dao.repository.IUserRepository;
import app.uma.vo.UserVO;


@Component
public class UserModel {

	@Autowired
	private IUserRepository userRepos;
	private HashMap<String, UserVO> users;
	public UserModel() {
		users = new HashMap<String, UserVO>();
	}

	
	public UserVO find(String uid) {
		UserVO user = new UserVO(userRepos.findOne(uid));
		return user;
	}




	public void init(String uid, int platId) {
		User user = new User();
		user.setId(uid);
		user.setPlatId(platId);
//		user.setAntiaddiction(0);
		userRepos.save(user);
	}

	
}