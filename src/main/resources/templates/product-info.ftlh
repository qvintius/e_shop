<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product-info</title>
</head>
<body>
<h1>BySell</h1>
<form action="/" method="get">
    <input type="submit" value="Назад">
</form>
<h4>Подробная информация о товаре</h4>

<!--вывод картинки (тег img) через отдельный контроллер. Получение картинок на товаре в контроллере товара и в цикле поочередно выводить через контроллер картинок-->
<#list images as img>
    <img src="/images/${img.id}" height="60px"><br><br>
</#list>
<b>Название товара: </b>${product.title}<br>
<b>Описание: </b>${product.description}<br>
<b>Цена: </b>${product.price}<br>
<b>Город: </b>${product.city}<br>
<b>Автор: </b><a href="/user/${product.user.id}">${product.user.name}</a><br><hr><!--Ссылка на продавца-->
<b>Номер телефона: </b>${product.user.phoneNumber}<br><hr>


<#if user.equals(product.user)><!--Пользователь может удалить только принадлежащий ему товар-->
    <form action="/product/delete/${product.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Удалить товар">
    </form>
</#if>

</body>
</html>