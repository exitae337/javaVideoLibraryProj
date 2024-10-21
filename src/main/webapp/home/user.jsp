<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="entity.User" %>

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
                    out.println("<p> ID пользователя: " + user.getUser_id() + "</p>");
                    out.println("<p> Имя пользователя: " +  user.getUser_fullname() + "</p>");
                    out.println("<p> E-mail пользователя: " + user.getUser_email() + "</p>");
                } else {
                    out.println("<b>Пользователя с таким ID не было найдено в базе данных!</b>");
                }
    %>
</body>
</html>