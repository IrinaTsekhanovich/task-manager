<%@ page import="webservice.model.Goal" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="webservice.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%  Goal goal = new Goal();
    try {
        goal = (Goal) request.getAttribute("goal");
    } catch (ClassCastException e) {
        e.printStackTrace();
    }

    ArrayList<Goal> subgoals = new ArrayList<Goal>();
    try {
        subgoals = (ArrayList<Goal>) request.getAttribute("subgoals");
    } catch (ClassCastException e) {
        e.printStackTrace();
    }

    ArrayList<Task> tasks = new ArrayList<Task>();
    try {
        tasks = (ArrayList<Task>) request.getAttribute("tasks");
    } catch (ClassCastException e) {
        e.printStackTrace();
    }
%>
<head>
    <title><%=goal.getName()%></title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<form method="get"  action="goals">
    <input type="submit" value="Вернуться к целям"/>
</form>
<h3><%=goal.getName()%></h3>
<ul>
    <form method="get" action="add_subgoal">
        <input type="number" hidden name="id" value="<%=goal.getId()%>"/>
        <input type="submit" value="Добавить подцель"/>
    </form>
<%if (subgoals.size()>0){
    for (Goal subgoal:subgoals) {%>
<p><%=subgoal.getName()%></p>
    <form method="get" action="select_goal">
        <input type="number" hidden name="id" value="<%=subgoal.getId()%>"/>
        <input type="submit" value="Подробнее"/>
    </form>
    <form method="get" action="update_goal">
        <input type="number" hidden name="id" value="<%=subgoal.getId()%>"/>
        <input type="submit" value="Редактировать"/>
    </form>
<form method="post" action="delete_goal">
    <input type="number" hidden name="id" value="<%=subgoal.getId()%>"/>
    <input type="submit" name="delete" value="Удалить"/>
</form>
<hr/>
<%} }%>
    <form method="get" action="add_task_to_goal">
        <input type="number" hidden name="id" value="<%=goal.getId()%>"/>
        <input type="submit" value="Добавить задачу"/>
    </form>
    <%if (tasks.size()>0){
        for (Task task : tasks) {%>
    <p>Задача: <%=task.getName()%></p>
    <p>Статус: <%=task.getStatus()?"Выполнена":"Не выполнена"%></p>
    <form method="post" action="delete_task_from_goal">
        <input type="number" hidden name="task_id" value="<%=task.getId()%>"/>
        <input type="number" hidden name="goal_id" value="<%=goal.getId()%>"/>
        <input type="submit" name="delete" value="Удалить"/>
    </form>
    <hr/>
    <% } } %>
</ul>
</body>
</html>