<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация в Видеотеке</title>
</head>
<body>
    <h1>Добро пожаловать новый пользователь!</h1>
    <p>Зарегистрируйтесь в системе чтобы получить доступ к её возможностям</p>
    <p></p>
    <form action="register" method="post">

        <label for="fullName">Ваше полное имя: </label>
        <input type="text" id="fullName" name="fullName" required>
        <p></p>
        <label for="email">E-mail: </label>
        <input type="text" id="email" name="email" required>
        <p></p>
        <label for="password">Password: </label>
        <input type="text" id="password" name="password" required>

        <button type="submit">Зарегистрироваться!</button>
    </form>
    <p>Уже зарегистрированы? Войдите в систему!</p>
    <button onclick="window.location.href='login'">Войти в систему</button>
</body>
</html>