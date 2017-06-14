package app.uma.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.uma.dao.entity.Pack;
import app.uma.dao.repository.IPackRepository;
import app.uma.net.socket.sessions.GameSession;
import app.uma.vo.RoleVO;

public class PackModel {

	@Autowired
	private IPackRepository packRepository;
	public void init(GameSession session) {
		RoleVO role = session.getRole(RoleVO.class);
		String rid = role.db.getId();
		List<Pack> pack = packRepository.getPacks(rid);
		if(pack == null || pack.size() == 0){
			
		}
	}

}
