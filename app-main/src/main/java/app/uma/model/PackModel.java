package app.uma.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.csv.CsvUtil;
import app.uma.dao.entity.Item;
import app.uma.dao.entity.Pack;
import app.uma.dao.repository.IPackRepository;
import app.uma.database.DtPack;
import app.uma.enums.PackEnum;
import app.uma.net.socket.data.GridData;
import app.uma.net.socket.data.PackData;
import app.uma.net.socket.sessions.GameSession;
import app.uma.vo.PackVO;
import app.uma.vo.RoleVO;

@Component
public class PackModel {

	@Autowired
	private IPackRepository packRepository;
	private HashMap<Integer,DtPack> mapDt;

	@SuppressWarnings("unchecked")
	public PackModel() throws Exception {
		mapDt = new HashMap<>();
		ArrayList<DtPack> list = (ArrayList<DtPack>) CsvUtil.getCsv("pack.dat",DtPack.class);
		for (DtPack dt : list){
			mapDt.put(dt.getType(), dt);
		}
	}
	public void init(GameSession session) {
		RoleVO role = session.getRole(RoleVO.class);
		String rid = role.db.getId();
//		List<Pack> listPack = packRepository.getPacks(rid);
		ArrayList<PackVO> packVOs = new ArrayList<>();
		PackData packData = new PackData();
		for(PackEnum e : PackEnum.values()){
			
			packData.setType(e.getType());
			DtPack dt = mapDt.get(e.getType());
			packData.setOpenLength(dt.getOpen());
			ArrayList<GridData> gridDatas = new ArrayList<>();
			packData.setItemList(gridDatas);
			Pack pack = packRepository.findByRoleIdAndType(rid, e.getType());
			if(pack == null){
				pack = new Pack();
				pack.setRoleId(rid);
				pack.setType(e.getType());
				packRepository.save(pack);
			}
			PackVO vo = new PackVO(pack);
			String itemStr = vo.getDb().getContent();
		}
	}
	
//	public void addItem(ItemitemId , GameSession session){
//		
//	}

}
