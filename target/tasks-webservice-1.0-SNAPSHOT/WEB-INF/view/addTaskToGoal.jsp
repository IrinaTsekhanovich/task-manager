<%@ page import="java.util.ArrayList" %>
<%@ page import="webservice.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить задачу в цель</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<%
    String goal = (String) request.getAttribute("goal_id");
    ArrayList<Task> tasks = new ArrayList<Task>();
    try {
        tasks = (ArrayList<Task>) request.getAttribute("tasks");
    } catch (ClassCastException e) {
        e.printStackTrace();
    }
%>
<form method="post" action="add_task_to_goal">
Выберите  свободную задачу: <select name="task">
    <% for (Task task : tasks){%>
    <option><%=task.getName()%></option>
    <%}%>
</select>
<br><br>
    <input type="number" hidden name="id" value="<%=goal%>"/>
    <input type="submit" name="add" value="Добавить"/>
</form>
</body>
</html>
