package app.uma.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.csv.CsvUtil;
import app.uma.dao.entity.Pack;
import app.uma.dao.repository.IPackRepository;
import app.uma.database.DtPack;
import app.uma.enums.PackEnum;
import app.uma.net.socket.data.PackData;
import app.uma.net.socket.response.PackInitResponse;
import app.uma.net.socket.sessions.GameSession;
import app.uma.vo.ItemVO;
import app.uma.vo.PackVO;
import app.uma.vo.RoleVO;

@Component
public class PackModel {

	@Autowired
	private IPackRepository packRepository;
	private HashMap<Integer,DtPack> dtPackMap;


	@SuppressWarnings("unchecked")
	public PackModel() throws Exception {
		dtPackMap = new HashMap<>();
		ArrayList<DtPack> list = (ArrayList<DtPack>) CsvUtil.getCsv("pack.dat",DtPack.class);
		for (DtPack dt : list){
			dtPackMap.put(dt.getType(), dt);
		}
	}
	
	public void init(GameSession session) throws Exception {
		RoleVO role = session.getRole(RoleVO.class);
		String rid = role.db.getId();
		ArrayList<PackData> packDatas = new ArrayList<>();
		for(PackEnum packEnum : PackEnum.values()){
			DtPack dt = dtPackMap.get(packEnum.getType());
			Pack pack = packRepository.findByRoleIdAndType(rid, packEnum.getType());
			if(pack == null){
				pack = new Pack();
				pack.setRoleId(rid);
				pack.setType(packEnum.getType());
				pack.setOpenLenth(dt.getOpen());
				packRepository.save(pack);
			}
			PackVO vo = PackVO.init(pack , dt);
			packDatas.add(vo.toMsg());
		}
		session.sendMsg(new PackInitResponse(200, packDatas));
	}
}
