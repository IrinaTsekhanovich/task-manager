<%@ page import="webservice.model.Goal" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мои цели</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<form method="get"  action="<c:url value='/tasks'/>">
    <input type="submit" value="Перейти к задачам"/>
</form>
<form method="get" action="<c:url value='/create_task'/>">
    <input type="submit" value="Добавить задачу"/>
</form>
<ul>
<%
    ArrayList<Goal> goals = new ArrayList<Goal>();
    try {
        goals = (ArrayList<Goal>) request.getAttribute("goals");
    } catch (ClassCastException e) {
        e.printStackTrace();
    }

    for (Goal goal:goals) {
%>
<p>Номер: <%=goal.getId()%></p>
<p>Описание: <%=goal.getName()%></p>
<form method="get" action="<c:url value='/update_task'/>">
    <input type="number" hidden name="id" value="${task.id}"/>
    <input type="submit" value="Редактировать"/>
</form>
<form method="post" action="<c:url value='/delete_task'/>">
    <input type="number" hidden name="id" value="${task.id}"/>
    <input type="submit" name="delete" value="Удалить"/>
</form>
    <hr/>
<% } %>
</ul>
</body>
</html>
