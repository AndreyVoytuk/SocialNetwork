<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>updateNews</title>
</head>
<body>
    <form action="updateData" method="POST">
        <p><textarea cols="10" rows="10" name="newText">${content}</textarea></p>
        <p><input type="submit" value="saveUpdate"></p>
    </form>
</body>
</html>
