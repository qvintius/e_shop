<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>BySell</title>
</head>
<body>
<h1>E-shop | <a href="/login">Войти</a></h1><hr>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Выйти"/>
</form><hr>
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
        <b>${product.title}</b> ${product.description} ${product.price} ${product.city} ${product.author}
        | <a href="/product/${product.id}">Подробнее...></a><p>
    </div>
    <#else><h3>Товаров нет</h3>
</#list>
<hr>
<h3>Создать новый товар</h3>
<form action="/product/create" method="post" enctype="multipart/form-data"><!--форма помимо обычных полей также принмает файлы-->
    Название объявления: <input type="text" name="title"/><br><br>
    Описание: <input type="text" name="description"/><br><br>
    Цена: <input type="number" name="price"/><br><br>
    Город: <input type="text" name="city"/><br><br>
    Автор: <input type="text" name="author"/><br><br>

    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить товар">
</form>

</body>
</html>