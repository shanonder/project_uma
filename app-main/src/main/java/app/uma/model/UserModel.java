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

	
	public UserVO findOrInit(int platId , int serverId, String openId ) {
		User user = userRepos.findByPlatIdAndServerIdAndOpenId(platId, serverId , openId);
		if(user == null){
			user = new User();
			user.setPlatId(platId);
			user.setServerId(serverId);
			user.setOpenId(openId);
			userRepos.save(user);
		}
		UserVO userVO = new UserVO(user);
		return userVO;
	}


}
