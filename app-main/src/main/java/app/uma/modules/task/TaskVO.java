package app.uma.modules.task;

import java.util.ArrayList;

public class TaskVO {

	private ArrayList<TaskNode> taskNodes;

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
		taskNodes = new ArrayList<>();
	}
	public void addNode(TaskNode node) {
		taskNodes.add(node);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}
