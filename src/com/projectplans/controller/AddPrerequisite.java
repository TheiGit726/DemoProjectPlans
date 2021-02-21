package com.projectplans.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projectplans.dao.AddTaskDaoImpl;

public class AddPrerequisite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddTaskDaoImpl addTaskDao = (AddTaskDaoImpl) request.getSession().getAttribute("addTaskDao"); 
		int currentTaskIndex = Integer.parseInt(request.getParameter("currentTaskIndex"));
		int prerequisiteId = Integer.parseInt(request.getParameter("prerequisite"));
		addTaskDao.getTaskRepository().get(currentTaskIndex).getPrerequisites().add(prerequisiteId);
		
		request.getRequestDispatcher("taskList.jsp").forward(request, response);
		
	}

}
