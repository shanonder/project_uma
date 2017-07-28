package app.uma.modules.task;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import app.uma.base.Ifactory;
import app.uma.csv.CsvUtil;
import app.uma.database.TaskCfg;
import app.uma.modules.task.clip.TaskClipCollcectItem;
import app.uma.modules.task.clip.TaskClipKillMonster;
import app.uma.modules.task.clip.TaskClipTalkToNpc;
import app.uma.modules.task.clip.TaskClipTimeLimit;

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
				TaskNode node = initNodeByCfg(cfg);
				vo.addNode(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private TaskNode initNodeByCfg(TaskCfg cfg) {
		String params = cfg.getParams();
		String[] strs = params.split(";");
		TaskNode node = new TaskNode();
		node.setNext(cfg.getNext());
		node.setReward(cfg.getReward());
		for(String cell: strs){
			String[] cells = cell.split("-");
			int type = Integer.parseInt(cells[0]);
			if(type == TaskTypeEnum.KILL.getType()){
				TaskClipKillMonster clip = new TaskClipKillMonster();
				clip.setMonsterId(Integer.parseInt(cells[1]));
				clip.setCount(Integer.parseInt(cells[2]));
				node.addClip(clip);
			}
			if(type == TaskTypeEnum.COLLECT.getType()){
				TaskClipCollcectItem clip = new TaskClipCollcectItem();
				clip.setItemId(Integer.parseInt(cells[1]));
				clip.setCount(Integer.parseInt(cells[2]));
				node.addClip(clip);
			}
			if(type == TaskTypeEnum.TALK.getType()){
				TaskClipTalkToNpc clip = new TaskClipTalkToNpc();
				clip.setNpcId(Integer.parseInt(cells[1]));
				node.addClip(clip);
			}
			if(type == TaskTypeEnum.TIME_LIMIT.getType()){
				TaskClipTimeLimit clip = new TaskClipTimeLimit();
				clip.setLimitTime(Integer.parseInt(cells[1]));
				node.addClip(clip);
			}
		}
		return node;
	}

}
