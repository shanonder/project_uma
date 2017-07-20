package app.uma.modules.task;

import java.util.ArrayList;

import app.uma.database.TaskCfg;

public class TaskVO {

	private ArrayList<TaskCfg> list;

	private int id;
	
	private String name;

	/**
	 * 配置ID
	 */
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public TaskVO() {
		list = new ArrayList<>();
	}
	public void addCfg(TaskCfg cfg) {
		list.add(cfg);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}
