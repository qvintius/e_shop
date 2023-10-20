<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
</head>
<body>
<h1>Панель администратора</h1><hr>
<form action="/" method="get">
    <input type="submit" value="Назад">
</form>

<table border="1">
    <tr><!--Шапка таблицы-->
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>

    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.phoneNumber}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}<#sep>, </#list></th><!--Вывод списка ролей с использованием сепаратора-->
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Бан"/>
                </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Редактировать</a></th>
            <th><a href="/user/${user.id}">Подробная информация</a></th>
        </tr>
    <#else><h3>Пользователей нет</h3>
    </#list>

</table>

</body>
</html>