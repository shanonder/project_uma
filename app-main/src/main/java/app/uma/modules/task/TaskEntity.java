package app.uma.modules.task;

import java.util.ArrayList;

public class TaskEntity {
	private ArrayList<TaskNode> taskNodes;	
	public TaskEntity() {
		taskNodes = new ArrayList<>();
	}
	
	public void addVO(TaskVO task){
		
	}
	
	public void addNode(TaskNode node){
		taskNodes.add(node);
	}
}
