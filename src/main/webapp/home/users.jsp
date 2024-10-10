<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Entities.User" %>

<html>
<head>
    <title>Все пользователи</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Список всех пользователей:</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>E-mail</th>
            <th>Роль</th>
        </tr>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            String errorMessage = (String) request.getAttribute("errorMessage");

            if (errorMessage != null) {
                out.println("<tr><td colspan='4'>" + errorMessage + "</td></tr>");
            } else if (users != null && !users.isEmpty()) {
                for (User user : users) {
                    out.println("<tr>");
                    out.println("<td>" + user.getId() + "</td>");
                    out.println("<td>" + user.getFullName() + "</td>");
                    out.println("<td>" + user.getEmail() + "</td>");
                    out.println("<td>" + user.getRole() + "</td>");
                    out.println("</tr>");
                }
            }
        %>
    </table>
</body>
</html>