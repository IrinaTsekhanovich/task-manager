<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новая цель</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<h2>Создание новой цели</h2>
<form method="post" action="add_goal">
    <label>Название: <input type="text" name="name"></label><br>
    <input type="submit" value="Добавить"><br>
</form>
<form method="get" action="goals">
    <input type="submit" value="Отменить"><br>
</form>
</body>
</html>
