<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login for to enter in profile</h1>
       <form action="LoginServlet" method="POST">
           <label>Login(email):<br> <input type="text" name="email"/></label><br>
           <label>Password:<br> <input type="password" name="password"/></label><br><br>
           <label><input type="checkbox" checked>subscribe to newsletter</label><br>
           <input type="submit" value="login"/>
       </form>
</body>
</html>
