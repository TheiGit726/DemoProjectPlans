<!DOCTYPE html>
<%@ page import="com.projectplans.model.Task,com.projectplans.dao.AddTaskDaoImpl" %>
<html>
<head>
<title></title>
</head>
<%
	int delay = (int) request.getSession().getAttribute("delay");
	int delayInSeconds = delay / 1000;
	String taskname = (String) request.getSession().getAttribute("taskname");
%>
<script>
	function moveLocation(delay){
		setTimeout(function(){document.location = 'taskList.jsp'}, delay);
	}
</script>
<body onload="moveLocation('<%=delay%>')">
	<p><%=taskname%> started duration <%=delayInSeconds%> seconds</p>
</body>
</html>