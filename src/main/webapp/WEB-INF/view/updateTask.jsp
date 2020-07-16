<%@ page import="webservice.model.Task" %>
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
<%Task task = new Task();
    try {
        task = (Task) request.getAttribute("task");
    } catch (ClassCastException e) {
        e.printStackTrace();
    }%>

<form method="post" action="update_task">
    <label><input type="number" hidden name="id" value="<%=task.getId()%>"/></label>
    <label>Название*: <input type="text" name="name" value="<%=task.getName()%>"></label><br>
    <label>Описание: <input type="text" name="description" value="<%=task.getDescription()%>"></label><br>
    <%if (task.getStatus()){%>
    <label>Статус*: <input type="radio" name="status" value="false" />Не выполнена</label>
    <label><input type="radio" name="status" value="true" checked />Выполнена</label><br><%} else {%>
    <label>Статус*: <input type="radio" name="status" value="false" checked />Не выполнена</label>
    <label><input type="radio" name="status" value="true" />Выполнена</label><br><%}%>
    <label>Срок выполнения: <input type="date" id="date" name="date" value="<%=task.getDate()%>"></label><br>
    <input type="submit" value="Сохранить"><br>
</form>
<form method="get" action="tasks">
    <input type="submit" value="Отменить"><br>
</form>
<p>* - эти поля обязательны для заполнения.</p>
</body>
</html>
