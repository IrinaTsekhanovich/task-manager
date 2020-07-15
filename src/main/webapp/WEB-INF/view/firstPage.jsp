<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Менеджер задач</title>
    <style>
        <%@include file="/WEB-INF/css/style.css" %>
    </style>
</head>
<body>
<form method="get"  action="<c:url value='/tasks'/>">
    <input type="submit" value="Задачи"/>
</form>
<form method="get" action="<c:url value='/goals'/>">
    <input type="submit" value="Цели"/>
</form>
</body>
</html>
