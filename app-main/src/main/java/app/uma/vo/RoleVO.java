package app.uma.vo;

import java.util.ArrayList;

import app.uma.dao.entity.Role;
import app.uma.net.socket.data.AttributesData;
import app.uma.net.socket.data.RoleData;
import app.uma.net.socket.sessions.GameSession;

public class RoleVO {
	
	public GameSession session;
	public Role db;
	public RoleVO(GameSession session , Role db){
		this.session = session;
		this.db = db;
	}
	

	
	public RoleData toMsg(){
		RoleData roleData = new RoleData();
		roleData.setName(db.getName());
		roleData.setProfId(db.getProfession());
		roleData.setExp(db.getExp());
		roleData.setInsId(db.getId());
		roleData.setLevel(db.getLevel());
		roleData.setAttributes(getAttributes());
		return roleData;
	}


	private ArrayList<AttributesData> getAttributes() {
		ArrayList<AttributesData> attributes = new ArrayList<>();
		AttributesData ad = new AttributesData();
		ad.setCfgId(1);
		ad.setValue(2);
		attributes.add(ad);
		ad.setCfgId(2);
		ad.setValue(4);
		attributes.add(ad);
		return attributes;
	}



	public GameSession getSession() {
		return session;
	}

}
