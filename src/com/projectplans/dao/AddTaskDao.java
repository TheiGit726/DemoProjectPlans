package com.projectplans.dao;

import java.util.List;

import com.projectplans.model.Task;

public interface AddTaskDao {
	
	public void addTask(String id, String name, String duration);
	
	public void addTask(Task t);
	
	public int getTaskIndex(Task t);
	
	public List <Task> allTasks();
	
	public Task getTaskById(int id);
		
	public List<Task> getTaskRepository();

	public List<Task> getValidPrerequisites(int currentTaskId);
	
	public boolean existInPrerequisite(int preReqId,int currentTaskId);
	
	public void setTaskRepository(List<Task> taskRepository);
	
	public boolean canTaskRun(Task t);
	
	public boolean canAllTaskRun(List<Task> taskList);
}
