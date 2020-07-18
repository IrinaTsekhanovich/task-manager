<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новая задача</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<h2>Создание новой задачи</h2>
    <form method="post" action="add_task">
        <label>Название*: <input type="text" name="name"></label><br>
        <label>Описание: <input type="text" name="description"></label><br>
        <label>*: <input type="radio" name="status" value="false" checked />Не выполнена</label>
        <label><input type="radio" name="status" value="true" />Выполнена</label><br>
        <label>Срок выполнения: <input type="date" id="date" name="date"></label><br>
        <input type="submit" value="Добавить"><br>
    </form>
    <form method="get" action="tasks">
        <input type="submit" value="Отменить"><br>
    </form>
<p>* - эти поля обязательны для заполнения.</p>
</body>
</html>
