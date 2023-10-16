<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
    <form action="/registration" method="post">
        Имя польхователя: <input type="text" name="name"><br><br>
        Эл. почта: <input type="email" name="email"><br><br>
        Номер телефона: <input type="text" name="phoneNumber"><br><br>
        Пароль: <input type="password" name="password"><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"></input>
        <input type="submit" value="Зарегистрироваться"><br>
    </form>

<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2><!--Если почта уже занята-->
</#if>

</body>
</html>