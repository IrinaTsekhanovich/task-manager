<%@ page import="webservice.model.Task" %>
<%@ page import="java.lang.ClassCastException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мои задачи</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<form method="get" action="goals">
    <input type="submit" value="Перейти к целям"/>
</form>
<h1>Мои задачи</h1>
<form method="get" action="add_task">
    <input type="submit" value="Добавить задачу"/>
</form>
<ul>
<%
ArrayList<Task> tasks = new ArrayList<Task>();
try {
    tasks = (ArrayList<Task>) request.getAttribute("tasks");
} catch (ClassCastException e) {
    e.printStackTrace();
}

for (Task task : tasks) {
%>
    <p>Задача: <%=task.getName()%></p>
<%if (task.getDescription()!=null&&task.getDescription().length()>0){%>
    <p>Описание: <%=task.getDescription()%></p><%}%>
    <p>Статус: <%=task.getStatus()?"Выполнена":"Не выполнена"%></p>
<%if (task.getDate()!=null){%>
    <p>Срок выполнения: <%=task.getDate()%></p><%}%>
        <form method="get" action="update_task">
            <input type="number" hidden name="id" value="<%=task.getId()%>"/>
            <input type="submit" value="Редактировать"/>
        </form>
    <form method="post" action="delete_task">
        <input type="number" hidden name="id" value="<%=task.getId()%>"/>
        <input type="submit" name="delete" value="Удалить"/>
    </form>
    <hr/>
<% } %>
</ul>

</body>
</html>
