<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Менеджер задач</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<h1>Персональный менеджер задач</h1>
<form method="get"  action="tasks">
    <input type="submit" value="Задачи"/>
</form>
<form method="get" action="goals">
    <input type="submit" value="Цели"/>
</form>
</body>
</html>
