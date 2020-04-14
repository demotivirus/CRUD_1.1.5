<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<form action = "/addUser" method="post">
    <input required type="text" name="name" placeholder="name">
    <input required type="password" name="password" placeholder="password">
    <input required type="number" name="age" placeholder="age">
    <input type="submit" value="Save">
</form>

</body>
</html>
