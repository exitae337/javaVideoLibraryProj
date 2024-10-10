<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Видеотека</title>
</head>
<body>
    <h1>Добро пожаловать!</h1>
    <p>На данной странице есть две кнопки - получение всех пользователей и конкретного пользователя по ID</p>
    <p></p>
    <button onclick="window.location.href='home/users'">Показать всех пользователй</button>
    <p></p>
    <form action="home/user" method="get">
        <label for="userId">Введите ID пользователя: </label>
        <input type="text" id="userId" name="userId">
        <button type="submit">Показать пользователя</button>
    </form>
    <p></p>
    <form action="home" method="post">
       <button type="submit">Выйти из системы</button>
    </form>

</body>
</html>