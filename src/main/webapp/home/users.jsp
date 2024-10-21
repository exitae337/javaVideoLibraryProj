<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.User" %>

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
                    out.println("<td>" + user.getUser_id() + "</td>");
                    out.println("<td>" + user.getUser_fullname() + "</td>");
                    out.println("<td>" + user.getUser_email() + "</td>");
                    out.println("<td>" + user.getUser_role_id() + "</td>");
                    out.println("</tr>");
                }
            }
        %>
    </table>
</body>
</html>