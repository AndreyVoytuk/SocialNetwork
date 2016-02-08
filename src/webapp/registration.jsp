<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h3>Registration for entered</h3>
<form action="RegistrationServlet" method="POST">
    <label>Username: <input type="text" name="username"></label><br>
    <label>Login(email): <input type="text" name="email"></label><br>
    <label>Password: <input type="password" name="password"></label><br>
    <label><input type="checkbox" checked>get news every day </label><br>
    <input type="submit" value="send">
</form>
</body>
</html>
