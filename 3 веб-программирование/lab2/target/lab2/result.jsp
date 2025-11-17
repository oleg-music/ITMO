<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="git.olegmusic.lab2.PointResult" %>

<%
    PointResult result = (PointResult) request.getAttribute("result");
%>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Результат проверки</title>
</head>
<body>

<h2>Результат проверки точки</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>X</th>
        <td><%= result.getXStr() %></td>
    </tr>
    <tr>
        <th>Y</th>
        <td><%= result.getYStr() %></td>
    </tr>
    <tr>
        <th>R</th>
        <td><%= result.getRStr() %></td>
    </tr>
    <tr>
        <th>Факт попадания</th>
        <td><%= result.isHit() ? "Попадание" : "Не попадание" %></td>
    </tr>
    <tr>
        <th>Текущее время</th>
        <td><%= result.getCurrentTime() %></td>
    </tr>
    <tr>
        <th>Время выполнения (ms)</th>
        <td><%= result.getExecTimeMs() %></td>
    </tr>
</table>

<br>

<a href="controller">Новый запрос</a>

</body>
</html>
