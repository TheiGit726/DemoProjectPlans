<!DOCTYPE html>
<%@ page import="com.projectplans.dao.AddTaskDaoImpl" %>
<html>
<head>
<title>Add Task</title>
</head>
<body>
	<div>
		<form action="AddTask" method="post">
			<p>Task ID: <input type="text" name="id"/></p>
			<p>Task name: <input type="text" name="name"/></p>
			<p>Task duration in seconds: 
				<SELECT name="duration">
					<option value="3">3</option>
					<option value="6">6</option>
					<option value="9">9</option>
				</SELECT>
			</p>
			<p>
				Prerequisites:
			</p>
			<p><input type="submit" value="Save Task" /></p>
		</form>
	</div>
</body>
</html>