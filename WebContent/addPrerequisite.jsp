<!DOCTYPE html>
<%@ page import="java.util.List,java.util.ArrayList,com.projectplans.model.Task,com.projectplans.dao.AddTaskDaoImpl" %>
<html>
<head>
<title>Add Prerequisite</title>
</head>
<body>
<div>
	<form>
		<p></p>
	</form>
</div>
	<%
	AddTaskDaoImpl addTaskDao = (AddTaskDaoImpl) request.getSession().getAttribute("addTaskDao");
	List<Task> taskList = (List<Task>) addTaskDao.allTasks();
	int taskid = Integer.parseInt(request.getParameter("id"));
	Task currentTask = addTaskDao.getTaskById(taskid);
	int currentTaskIndex = addTaskDao.getTaskIndex(currentTask);
	int id, duration;
	String name;
	List<Task> prerequisites = addTaskDao.getValidPrerequisites(taskid);
	

		%>
	<div>
		<form action="AddPrerequisite" method="post">
			<p>Task ID: 
				<input type="text" name="id" value="<%=currentTask.getId()%>" disabled="disabled"/>
				<input type="hidden" name="currentTaskIndex"  value="<%=currentTaskIndex%>" />
			</p>
			<p>Task name: <input type="text" name="name" value="<%=currentTask.getName()%>"  disabled="disabled" /></p>
			<p>Task list: 
				<SELECT name="prerequisite" id="prerequisite">
				<%
				for(Task t:prerequisites){
					id = t.getId();
					name = t.getName();
					if(id!=taskid){
						if(!prerequisites.contains(id)){
						%>
							<option value="<%=id%>"><%=id%> - <%=name%></option>
						<%
						}
					}
				}
				%>
					
				</SELECT>
			</p>
			<p>
				Prerequisites:
				<%=currentTask.prerequisitesToString(addTaskDao) %>
			</p>
			<p><input type="submit" value="Add Prerequisite" /></p>
			<p><a href="taskList.jsp">Task List</a></p>
		</form>
	</div>
</body>
</html>