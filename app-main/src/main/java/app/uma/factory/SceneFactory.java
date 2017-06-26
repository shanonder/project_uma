package app.uma.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import app.uma.csv.CsvUtil;
import app.uma.database.DtScene;

public class SceneFactory implements Ifactory {
	@Autowired
	private CsvUtil csvUtil;
	private HashMap<Integer, DtScene> cfg;
	@Override
	public void initCfgs() {
		setCfg(new HashMap<>());
		ArrayList<DtScene> scenes;
		try {
			scenes = csvUtil.getCsv("scene.dat",DtScene.class);
			for(DtScene dtscene : scenes ){
				getCfg().put(dtscene.getId(), dtscene);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public HashMap<Integer, DtScene> getCfg() {
		return cfg;
	}
	public void setCfg(HashMap<Integer, DtScene> cfg) {
		this.cfg = cfg;
	}

}
