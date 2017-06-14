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

	
	public UserVO findOrInit(int platId , String platKey ) {
		User user = userRepos.getUserById(platKey, platId);
		if(user == null){
			user = new User();
			user.setPlatKey(platKey);
			user.setPlatId(platId);
//			user.setAntiaddiction(0);
			userRepos.save(user);
		}
		UserVO userVO = new UserVO(user);
		return userVO;
	}


}
