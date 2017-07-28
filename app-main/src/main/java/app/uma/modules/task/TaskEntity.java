package app.uma.modules.task;

import java.util.ArrayList;

import app.uma.modules.task.clip.TaskClip;

public class TaskEntity {
	private ArrayList<TaskClip> taskNodes;	
	public TaskEntity() {
		taskNodes = new ArrayList<>();
	}
	
	public void addVO(TaskVO task){
		
	}
	
	public void addNode(TaskClip node){
		taskNodes.add(node);
	}
}
