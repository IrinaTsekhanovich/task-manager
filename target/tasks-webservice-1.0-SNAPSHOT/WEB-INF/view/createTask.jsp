<%@ page import="webservice.model.Task" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<%Task task = new Task();
    try {
        task = (Task) request.getAttribute("task");
    } catch (ClassCastException e) {
        e.printStackTrace();
    }%>

    <form method="post" action="update_task">
        <input type="number" hidden name="id" value="<%=task.getId()%>"/>
        Название*: <input type="text" name="name" value="<%=task.getName()%>"><br>
        Описание: <input type="text" name="description" value="<%=task.getDescription()%>"><br>
        Статус*: <input type="radio" name="status" value="false" checked />Не выполнена
        <input type="radio" name="status" value="true" />Выполнена<br>
        Срок выполнения: <input type="date" id="date" name="date" value="<%=task.getDate()%>"><br>
        <input type="submit" value="Сохранить"><br>
    </form>
    <form method="get" action="task">
        <input type="submit" value="Отменить"><br>
    </form>
<p>* - эти поля обязательны для заполнения.</p>
</body>
</html>
