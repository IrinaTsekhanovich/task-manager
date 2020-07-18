<%@ page import="webservice.model.Goal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать цель</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<h2>Редактировать цель</h2>
<%
    Goal goal = new Goal();
    try {
        goal = (Goal) request.getAttribute("goal");
    } catch (ClassCastException e) {
        e.printStackTrace();
    }%>
<form method="post" action="update_goal">
    <label><input type="number" hidden name="id" value="<%=goal.getId()%>"/></label>
    <label>Название*: <input type="text" name="name" value="<%=goal.getName()%>"></label><br>
    <input type="submit" value="Сохранить"><br>
</form>
<form method="get" action="goals">
    <input type="submit" value="Отменить"><br>
</form>
</body>
</html>
