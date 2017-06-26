package app.uma.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.dao.entity.Pack;
import app.uma.dao.repository.IPackRepository;
import app.uma.database.DtPack;
import app.uma.enums.PackEnum;
import app.uma.factory.PackFactory;
import app.uma.modules.pack.processer.PackDeleteProcesser;
import app.uma.modules.pack.processer.PackInitProcesser;
import app.uma.modules.pack.processer.PackMoveProcesser;
import app.uma.modules.pack.processer.PackSellProcesser;
import app.uma.net.socket.consts.ProtocolConst;
import app.uma.net.socket.data.PackData;
import app.uma.net.socket.response.PackInitResponse;
import app.uma.net.socket.sessions.GameSession;
import app.uma.vo.PackVO;
import app.uma.vo.RoleVO;

@Component
public class PackModel extends ModelBase{

	@Autowired
	private IPackRepository packRepository;

	@Autowired
	private PackFactory packFactory;
	



	public PackModel(){
		
	}
	
	public void init(GameSession session) throws Exception {
		RoleVO role = session.getRole(RoleVO.class);
		String rid = role.db.getId();
		ArrayList<PackVO> packVOs = new ArrayList<>();
		ArrayList<PackData> packDatas = new ArrayList<>();
		for(PackEnum packEnum : PackEnum.values()){
			DtPack dt = packFactory.getDtPackMap().get(packEnum.getType());
			Pack pack = packRepository.findByRoleIdAndType(rid, packEnum.getType());
			if(pack == null){
				pack = new Pack();
				pack.setRoleId(rid);
				pack.setType(packEnum.getType());
				pack.setOpenLenth(dt.getOpen());
				packRepository.save(pack);
			}
			PackVO vo = packFactory.initPack(pack, dt);
			packVOs.add(vo);
			packDatas.add(vo.toMsg());
		}
		session.setPack(packVOs);
		session.sendMsg(new PackInitResponse(200, packDatas));
	}

	
	
	@Override
	public void registProsesser() {
		registProcess(ProtocolConst.PackInitRequest, PackInitProcesser.class);
		registProcess(ProtocolConst.PackMoveRequest, PackMoveProcesser.class);
		registProcess(ProtocolConst.PackDeleteRequest, PackDeleteProcesser.class);
		registProcess(ProtocolConst.PackSellRequest, PackSellProcesser.class);
	}

	@Override
	public void startup() {
		
	}
}
