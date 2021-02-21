<!DOCTYPE html>
<%@ page import="java.util.List,java.util.ArrayList,com.projectplans.model.Task,com.projectplans.dao.AddTaskDaoImpl" %>
<html>
<head>
<title>Task List</title>
<script>
	function addPrerequisite(id){
		window.location.href = "addPrerequisite.jsp?id="+id;
	}
</script>
</head>
<body>
<table>
	<tr>
		<td>ID</td>
		<td>NAME</td>
		<td>DURATION</td>
		<td>PREREQUISITES</td>
		<td>STATUS</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
<%
	AddTaskDaoImpl addTaskDao = (AddTaskDaoImpl) request.getSession().getAttribute("addTaskDao");
	List<Task> taskList = (List<Task>) addTaskDao.allTasks();
	int id, duration;
	String name;
	String prerequisites;
	String status;
	boolean canRun;
	boolean canAllTaskRun = addTaskDao.canAllTaskRun(taskList);
	for(Task t:taskList){
		id = t.getId();
		duration = t.getDuration();
		name = t.getName();
		canRun = addTaskDao.canTaskRun(t);
		prerequisites = t.prerequisitesToString(addTaskDao);
		status = t.getStatusToString();
		
		%>
			<tr>
				<td><%=id%></td>
				<td><%=name%></td>
				<td><%=duration%></td>
				<td><%=prerequisites%></td>
				<td><%=status%></td>
				<td>
					<%if(taskList.size()>1){ %>
						<input type="button" value="Add Prerequisites" onclick="addPrerequisite('<%=id%>');" />
					<%}else{ %>
						&nbsp;
					<%} %>
				</td>
				<td>
					<%if(canRun==true && t.getStatus()!=2){ %>
					<form action="RunTask" method="post">
						<input type="hidden" value="<%=id%>"  name="id" />
						<input type="submit" value="Run" />
					</form>
					<%}else{ %>
						Not runnable.
					<%} %>
				</td>
			</tr>
		<%
	}
%>
<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>

<p><a href="addTask.jsp">Add Task</a></p>
</body>
</html>