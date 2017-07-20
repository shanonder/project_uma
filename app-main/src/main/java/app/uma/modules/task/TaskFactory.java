package app.uma.modules.task;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import app.uma.base.Ifactory;
import app.uma.csv.CsvUtil;
import app.uma.database.TaskCfg;

public class TaskFactory implements Ifactory {

	@Autowired
	private CsvUtil csvUtil;
	private HashMap<Integer,TaskVO> taskMap;
	
	@Override
	public void initCfgs() {
		taskMap = new HashMap<>();
		try {
			ArrayList<TaskCfg> list = csvUtil.getCsv("task.dat",TaskCfg.class);
			for (TaskCfg cfg : list){
				TaskVO vo;
				if(taskMap.containsKey(cfg.getId()) == false){
					vo = new TaskVO();
					vo.setId(cfg.getId());
					vo.setName(cfg.getName());
					taskMap.put(cfg.getId(), vo);
				}else{
					vo = taskMap.get(cfg.getId());
				}
				vo.addCfg(cfg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
