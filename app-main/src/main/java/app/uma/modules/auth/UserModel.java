package app.uma.modules.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.base.ModelBase;
import app.uma.dao.entity.User;
import app.uma.dao.repository.IUserRepository;
import app.uma.modules.auth.processer.LoginProcesser;
import app.uma.net.socket.consts.ProtocolConst;
import app.uma.net.socket.heaps.HeapProcesser;


@Component
public class UserModel extends ModelBase{

	@Autowired
	private IUserRepository userRepos;
	public UserModel() {
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




	@Override
	public void registProsesser() {
		registProcess(ProtocolConst.HeapRequest, HeapProcesser.class);
		registProcess(ProtocolConst.LoginRequest, LoginProcesser.class);
		
	}


	@Override
	public void startup() {
		
	}


}
