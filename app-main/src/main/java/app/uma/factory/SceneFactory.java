package app.uma.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.uma.csv.CsvUtil;
import app.uma.database.SceneCfg;
import app.uma.enums.SceneEnum;
import app.uma.vo.DungeonSceneEntity;
import app.uma.vo.SceneEntity;
import app.uma.vo.WildSceneEntity;

@Component
public class SceneFactory implements Ifactory {
	@Autowired
	private CsvUtil csvUtil;
	private HashMap<Integer, SceneCfg> cfgMap;
	@Override
	public void initCfgs() {
		setCfg(new HashMap<>());
		ArrayList<SceneCfg> scenes;
		try {
			scenes = csvUtil.getCsv("scene.dat",SceneCfg.class);
			for(SceneCfg sceneCfg : scenes ){
				getCfg().put(sceneCfg.getId(), sceneCfg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public HashMap<Integer, SceneCfg> getCfg() {
		return cfgMap;
	}
	public void setCfg(HashMap<Integer, SceneCfg> cfgMap) {
		this.cfgMap = cfgMap;
	}
	public SceneEntity initScene(SceneCfg cfg) {
		SceneEntity entity = null;
		if(cfg.getType() == SceneEnum.WILD.getType()){
			entity = new WildSceneEntity();
		}else if(cfg.getType() == SceneEnum.DUNGEON.getType()){
			entity = new DungeonSceneEntity();
		}
		entity.setCfg(cfg);
		return entity;
	}

}
