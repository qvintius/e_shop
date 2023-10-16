<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>E-shop</h1>
    <h4>Авторизация</h4>
    <form action="/login" method="post"> <!--login получаем от security-->
        Эл. почта: <input type="email" name="username"><br><br>
        Пароль: <input type="password" name="password"><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Вход"><br>
    </form><br>
    <a href="/registration">Зарегистрироваться</a>
</body>
</html>