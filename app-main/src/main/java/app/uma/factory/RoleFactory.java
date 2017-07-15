package app.uma.factory;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import app.uma.csv.CsvUtil;
import app.uma.database.RoleCfg;

public class RoleFactory implements Ifactory {

	@Autowired
	private CsvUtil csvUtil;
	
	private HashMap<Integer,RoleCfg> roleCfgs; 
	@Override
	public void initCfgs() {
		roleCfgs = new HashMap<>();
		ArrayList<RoleCfg> roleCfgs;
		try {
			roleCfgs = csvUtil.getCsv("role.dat",RoleCfg.class);
			for(RoleCfg roleCfg : roleCfgs ){
				this.roleCfgs.put(roleCfg.getId(), roleCfg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
