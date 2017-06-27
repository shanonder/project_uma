package app.uma.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.csv.CsvUtil;
import app.uma.database.MonsterCfg;
import app.uma.modules.scene.ISceneEntity;
import app.uma.vo.MonsterEntity;

@Component
public class MonsterFactory implements Ifactory {
	@Autowired
	private CsvUtil csvUtil;
	private HashMap<Integer, MonsterCfg> hashMap;
	@Override
	public void initCfgs() {
		hashMap = new HashMap<>();
		ArrayList<MonsterCfg> monsterCfgs;
		try {
			monsterCfgs = csvUtil.getCsv("monster.dat",MonsterCfg.class);
			for(MonsterCfg monsterCfg : monsterCfgs ){
				hashMap.put(monsterCfg.getId(), monsterCfg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public ISceneEntity initMonster(int cfgId, double posX, double posY) {
		
		MonsterEntity entity = new MonsterEntity();
		entity.setInsId(UUID.randomUUID().toString());
		entity.setPosX(posX);
		entity.setPosY(posY);
		entity.setCfg(hashMap.get(cfgId));
		return entity;
	}

}
