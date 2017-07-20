package app.uma.modules.pack;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.base.ModelBase;
import app.uma.dao.entity.Pack;
import app.uma.dao.repository.IPackRepository;
import app.uma.database.PackCfg;
import app.uma.modules.pack.processer.PackDeleteProcesser;
import app.uma.modules.pack.processer.PackInitProcesser;
import app.uma.modules.pack.processer.PackMoveProcesser;
import app.uma.modules.pack.processer.PackSellProcesser;
import app.uma.modules.role.RoleVO;
import app.uma.net.socket.consts.ProtocolConst;
import app.uma.net.socket.data.PackData;
import app.uma.net.socket.response.PackInitResponse;
import app.uma.net.socket.sessions.GameSession;

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
		ArrayList<PackEntity> packVOs = new ArrayList<>();
		ArrayList<PackData> packDatas = new ArrayList<>();
		for(PackEnum packEnum : PackEnum.values()){
			PackCfg cfg = packFactory.getDtPackMap().get(packEnum.getType());
			Pack pack = packRepository.findByRoleIdAndType(rid, packEnum.getType());
			if(pack == null){
				pack = new Pack();
				pack.setRoleId(rid);
				pack.setType(packEnum.getType());
				pack.setOpenLenth(cfg.getOpen());
				packRepository.save(pack);
			}
			PackEntity vo = packFactory.initPack(pack, cfg);
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
