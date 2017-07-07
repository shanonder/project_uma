package app.uma.modules.npc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.base.Ifactory;
import app.uma.csv.CsvUtil;
import app.uma.database.MonsterCfg;

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

	public MonsterEntity initMonster(int cfgId, double posX, double posY) {
		
		MonsterEntity entity = new MonsterEntity();
		entity.setInsId(UUID.randomUUID().toString());
		entity.setPosX(posX);
		entity.setPosY(posY);
		entity.setCfg(hashMap.get(cfgId));
		return entity;
	}

}
