<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>User</title>
</head>
<body>
<h3>Подробная информация о пользователе ${user.name}</h3>
<#if user.avatar??><!--Если пользователь загружал свой аватар-->
    <img src="/images/${user.avatar.id}" height="60px"><br>
</#if>
<b>Имя пользователя: ${user.name}</b>
<b>Номер телефона: </b> ${user.phoneNumber}</br>
<b>Email: ${user.email}</b>

<h4>Товры пользователя ${user.name}</h4>
<#list products as product>
    <div>
        <b>${product.title}</b> ${product.description} ${product.price} ${product.city} ${product.user.name}
        | <a href="/product/${product.id}">Подробнее...></a><p>
    </div>
<#else><h3>Товаров нет</h3>
</#list>



</body>
</html>