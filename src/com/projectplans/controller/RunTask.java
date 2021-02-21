package com.projectplans.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projectplans.dao.AddTaskDaoImpl;
import com.projectplans.model.Task;

public class RunTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddTaskDaoImpl addTaskDao = (AddTaskDaoImpl) request.getSession().getAttribute("addTaskDao");
		int taskId = Integer.parseInt(request.getParameter("id"));
		Task t = addTaskDao.getTaskById(taskId);
		t.setStatus(2);
		t.clearPrerequisites();
		int delay = t.getDuration()*1000;
		request.getSession().setAttribute("delay", delay);
		request.getSession().setAttribute("taskname", t.getName());
		request.getRequestDispatcher("loadPage.jsp").forward(request, response);
	}

}
