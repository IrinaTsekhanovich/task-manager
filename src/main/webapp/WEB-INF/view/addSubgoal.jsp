<%--
  Created by IntelliJ IDEA.
  User: irats
  Date: 18.07.2020
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новая подцель</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<h2>Создание новой подцели</h2>
<%final String id = request.getParameter("id");%>
<form method="post" action="add_subgoal">
    <label>Название: <input type="text" name="name"></label><br>
    <input type="number" hidden name="id" value="<%=id%>"/>
    <input type="submit" value="Добавить"><br>
</form>
<form method="get" action="goals?id=<%=id%>">
    <input type="submit" value="Отменить"><br>
</form>
</body>
</html>
