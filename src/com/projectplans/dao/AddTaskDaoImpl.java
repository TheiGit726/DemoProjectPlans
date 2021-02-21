package com.projectplans.dao;

import java.util.ArrayList;
import java.util.List;

import com.projectplans.model.Task;

public class AddTaskDaoImpl implements AddTaskDao {
	
	private List<Task> taskRepository = new ArrayList<Task>();

	@Override
	public List<Task> getTaskRepository() {
		return taskRepository;
	}

	@Override
	public void setTaskRepository(List<Task> taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void addTask(String id, String name, String duration) {
		Task task = new Task(Integer.parseInt(id), name, Integer.parseInt(duration));
		this.taskRepository.add(task);

	}

	@Override
	public List<Task> allTasks() {
		return taskRepository;
	}

	@Override
	public void addTask(Task t) {
		taskRepository.add(t);
	}

	@Override
	public int getTaskIndex(Task t) {
		int index = taskRepository.indexOf(t);
		return index;
	}

	@Override
	public Task getTaskById(int id) {
		Task currentTask = null;
		List<Task> taskList = taskRepository;
		for(Task t: taskList) {
			if(t.getId()==id) {
				currentTask = t;
			}
		}
		return currentTask;
	}

	@Override
	public List<Task> getValidPrerequisites(int currentTaskId) {
		List<Task> validPrerequisites = new ArrayList<Task>();
		Task currentTask = getTaskById(currentTaskId);
		int id;
		for(Task t:taskRepository){
			id = t.getId();
			if(t.getStatus()<2) {
				if(id!=currentTaskId){//not equal
					if(!currentTask.getPrerequisites().contains(id)){// not yet in prerequisite
						if(existInPrerequisite(id,currentTaskId)==false) {//Task id is not prerequisite of prerequisite
							if(t.getPrerequisites().size()==0) {
								validPrerequisites.add(getTaskById(id));
							}
						}
					}
				}
			}
		}
		return validPrerequisites;
	}

	@Override
	public boolean existInPrerequisite(int preReqId,int currentTaskId) {
		boolean result = false;
		if(getTaskById(preReqId).getPrerequisites().contains(currentTaskId)) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean canTaskRun(Task t) {
		boolean result = true;
		if(t.getPrerequisites()!=null) {
			for(int prereqId : t.getPrerequisites()) {
				if(getTaskById(prereqId).getStatus()!=2) {
					result = false;
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean canAllTaskRun(List<Task> taskList) {
		boolean result = false;
		if(taskList!=null) {
			for(Task task : taskList) {
				if(task.getStatus()==0) {
					result = true;
				}
			}
		}
		return result;
	}
	
	public List<Task> getTaskWithNoPrerequisites(){
		List<Task> taskWithNoPrerequisites = new ArrayList<Task>();
		
		for(Task t: taskRepository) {
			if(t.getPrerequisites().size()<1&&t.getStatus()<2) {
				taskWithNoPrerequisites.add(t);
			}
		}
		return taskWithNoPrerequisites;
	}
	
	public List<Task> getTaskWithExuctedPrerequisites(){
		List<Task> taskWithNoPrerequisites = new ArrayList<Task>();
		for(Task t: taskRepository) {
			if(canTaskRun(t)==true&&t.getStatus()<2) {
				taskWithNoPrerequisites.add(t);
			}
		}
		return taskWithNoPrerequisites;
	}
	
	public List<Task> getNotExecutedTasks(){
		List<Task> taskWithNoPrerequisites = new ArrayList<Task>();
		for(Task t: taskRepository) {
			if(t.getStatus()<2) {
				taskWithNoPrerequisites.add(t);
			}
		}
		return taskWithNoPrerequisites;
	}


}
