<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User edit</title>
</head>
<body>
<h3>Редактирвание пользователя ${user.name}</h3>
    <form action="/admin/user/edit" method="post">
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label><!--Стоит галочка на имеющихся у пользователя ролях-->
            </div>
        </#list>
        <input type="hidden" name="userId" value="${user.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Сохранить</button>
    </form>
</body>
</html>