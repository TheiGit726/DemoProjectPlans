package com.projectplans.model;

import java.util.ArrayList;
import java.util.List;

import com.projectplans.dao.AddTaskDaoImpl;

public class Task{
	private int id;
	private String name;
	private int duration;
	private int status;
	private List<Integer> prerequisites = new ArrayList<Integer>();
	
	public Task(int id,String name, int duration) {
		this.id = id;
		this.name = name;
		this.duration = duration;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void clearPrerequisites() {
		List<Integer> p = this.prerequisites;
		p.clear();
		setPrerequisites(p);
	}
	
	public String getStatusToString() {
		String statusString = "";
		if(this.status==0) {
			statusString = "Not Started";
		}else if (this.status==1) {
			statusString = "Started";
		}else if (this.status==2) {
			statusString = "End";
		}
		return statusString;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public ArrayList<Integer> getPrerequisites() {
		return (ArrayList<Integer>) prerequisites;
	}
	
	public String prerequisitesToString(AddTaskDaoImpl addTaskDao) {
		String preReqs = "NONE";
		Task t;
		if(prerequisites!=null) {
			if(prerequisites.size()>0) {
				StringBuilder sb = new StringBuilder();
				ArrayList<Integer> p = (ArrayList<Integer>) prerequisites;
				for(int preReq: p) {
					t = addTaskDao.getTaskById(preReq);
					if(p.size()>1) {
						sb.append(preReq+"("+t.getStatusToString()+") ");
					}else if(p.size()==1){
						sb.append(preReq+"("+t.getStatusToString()+")");
					}
					
				}
				preReqs =  sb.toString();
			}
		}
		return preReqs;
	}

	public void setPrerequisites(List<Integer> prerequisites) {
		this.prerequisites = prerequisites;
	}
	
}
