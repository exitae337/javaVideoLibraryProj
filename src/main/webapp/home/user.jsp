<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="Entities.User" %>

<html>
<head>
    <title>Все пользователи</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Выбранный пользователь:</h1>
    <%
                User user = (User) request.getAttribute("user");

                if (user != null) {
                    out.println("<p> ID пользователя: " + user.getId() + "</p>");
                    out.println("<p> Имя пользователя: " +  user.getFullName() + "</p>");
                    out.println("<p> E-mail пользователя: " + user.getEmail() + "</p>");
                } else {
                    out.println("<b>Пользователя с таким ID не было найдено в базе данных!</b>");
                }
    %>
</body>
</html>