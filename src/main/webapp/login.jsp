<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход в Видеотеку</title>
</head>
<body>
    <h1>Добро пожаловать в систему</h1>
    <p>Введите e-mail и пароль чтобы войти в систему.</p>
    <p>Зарегистрируйтесь если еще не сделали этого.</p>
    <form action="login" method="post">

        <label for="email">E-mail: </label>
        <input type="text" id="email" name="email" required>

        <label for="password">Password: </label>
        <input type="text" id="password" name="password" required>

        <button type="submit">Войти!</button>
    </form>
    <p></p>
    <button onclick="window.location.href='register'">Регистрация</button>
</body>
</html>