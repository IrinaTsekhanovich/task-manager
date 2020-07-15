<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать задачу</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<h2>Редактировать задачу</h2>
<form method="post" action="add_task">
    Название*: <input type="text" name="name"><br>
    Описание: <input type="text" name="description"><br>
    Статус*: <input type="radio" name="status" value="false" checked />Не выполнена
    <input type="radio" name="status" value="true" />Выполнена<br>
    Срок выполнения: <input type="date" id="date" name="date"><br>
    <input type="submit" value="Добавить"><br>
</form>
<form method="get" action="tasks">
    <input type="submit" value="Отменить"><br>
</form>
<p>* - эти поля обязательны для заполнения.</p>
</body>
</html>
