package app.uma.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import app.uma.csv.CsvUtil;
import app.uma.database.SceneCfg;

public class SceneFactory implements Ifactory {
	@Autowired
	private CsvUtil csvUtil;
	private HashMap<Integer, SceneCfg> cfg;
	@Override
	public void initCfgs() {
		setCfg(new HashMap<>());
		ArrayList<SceneCfg> scenes;
		try {
			scenes = csvUtil.getCsv("scene.dat",SceneCfg.class);
			for(SceneCfg dtscene : scenes ){
				getCfg().put(dtscene.getId(), dtscene);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public HashMap<Integer, SceneCfg> getCfg() {
		return cfg;
	}
	public void setCfg(HashMap<Integer, SceneCfg> cfg) {
		this.cfg = cfg;
	}

}
