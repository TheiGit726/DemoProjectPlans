package com.projectplans.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projectplans.dao.AddTaskDao;
import com.projectplans.dao.AddTaskDaoImpl;
import com.projectplans.model.Task;


public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AddTaskDaoImpl addTaskDao = new AddTaskDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String duration = request.getParameter("duration");
		
		addTaskDao.addTask(id, name, duration);
		
		request.getSession().setAttribute("addTaskDao", addTaskDao);
		request.getRequestDispatcher("taskList.jsp").forward(request, response);
	}

}
