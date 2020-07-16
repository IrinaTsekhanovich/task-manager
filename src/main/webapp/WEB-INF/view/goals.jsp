<%@ page import="webservice.model.Goal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мои цели</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<form method="get"  action="tasks">
    <input type="submit" value="Перейти к задачам"/>
</form>
<h1>Мои цели</h1>
<form method="get" action="add_goal">
    <input type="submit" value="Добавить цель"/>
</form>
<ul>
<%
    ArrayList<Goal> goals = new ArrayList<Goal>();
    try {
        goals = (ArrayList<Goal>) request.getAttribute("goals");
    } catch (ClassCastException e) {
        e.printStackTrace();
    }
    for (Goal goal:goals) {%>
<p><%=goal.getName()%></p>
    <form method="get" action="select_goal">
        <input type="number" hidden name="id" value="<%=goal.getId()%>"/>
        <input type="submit" value="Подробнее"/>
    </form>
<form method="get" action="update_goal">
    <input type="number" hidden name="id" value="<%=goal.getId()%>"/>
    <input type="submit" value="Редактировать"/>
</form>
<form method="post" action="delete_goal">
    <input type="number" hidden name="id" value="<%=goal.getId()%>"/>
    <input type="submit" name="delete" value="Удалить"/>
</form>
    <hr/>
<% } %>
</ul>
</body>
</html>
