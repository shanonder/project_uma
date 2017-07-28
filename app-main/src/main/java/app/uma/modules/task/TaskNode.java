package app.uma.modules.task;

import java.util.ArrayList;

import app.uma.modules.task.clip.TaskClip;

public class TaskNode {
	private ArrayList<TaskClip> taskClips;
	private int next;
	private String reward;
	public TaskNode() {
		taskClips = new ArrayList<>();
	}
	
	public void addClip(TaskClip clip){
		taskClips.add(clip);
	}

	public void setNext(int next) {
		this.next = next;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public ArrayList<TaskClip> getTaskClips() {
		return taskClips;
	}

	public int getNext() {
		return next;
	}

	public String getReward() {
		return reward;
	}
}
