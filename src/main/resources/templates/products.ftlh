<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>BySell</title>
</head>
<body>
<h1>E-shop</h1><hr>
<#if user.email??>
    <h3>Имя пользователя: ${user.name}</h3>
    <#if user.avatar??><!--Если пользователь загружал свой аватар-->
        <img src="/images/${user.avatar.id}" height="60px"><br><br>
    </#if>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <#if user.isAdmin()>
        <a href="/admin">Панель администратора</a>
    </#if>
<#else>
    <a href="/login">Войти</a>
</#if><hr>

<h4>Товары России</h4>
<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="title"><br>
    <input type="submit" value="Поиск"/>
</form>
<br>
<form action="/" method="get">
     <input type="submit" value="Сбросить поиск"/>
</form>

<#list products as product>
    <div>
        <b>${product.title}</b> ${product.description} ${product.price} ${product.city} ${product.user.name}
        | <a href="/product/${product.id}">Подробнее...></a><p>
    </div>
    <#else><h3>Товаров нет</h3>
</#list><hr>

<#if user.email??>
<h3>Создать новый товар</h3>
<form action="/product/create" method="post" enctype="multipart/form-data"><!--форма помимо обычных полей также принмает файлы-->
    Название объявления: <input type="text" name="title"/><br><br>
    Описание: <input type="text" name="description"/><br><br>
    Цена: <input type="number" name="price"/><br><br>
    Город: <input type="text" name="city"/><br><br>

    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить товар"/>
</form>
</#if>

</body>
</html>