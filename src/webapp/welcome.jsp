<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="date" uri="http://socialNetwork/jsp/customTag" %>
<html>
<head>
    <title>Welcome user</title>
</head>
<body>
    <div align="center"><h1>Welcome ${user.userName}</h1>
    <h4>Time since for your login  <date:currentlyDate/></h4>
    <p><h2>Social network in our life!</h2></p>
    <p> We don`t imagine our lives without Internet</p>
    <h3>Do you want to create a new article?</h3>
    <form action="addPost" method="POST">
        <p><textarea cols="10" rows="2" name="title">Enter title</textarea></p>
        <p><textarea cols="40" rows="5" name="content">Enter new post</textarea></p>
        <p><input type="submit" value="add"></p>
    </form>
    <div>
        <c:forEach var="post" items="${allPosts}">
            <div>
                Author # <c:out value="${post.postAuthor}"/><br>
                <c:out value="${post.postContent}"/><br>
                <c:if test="${post.postId == likePost}">
                    Amount likes "+": <c:out value="${amountLikes}"/><br>
                </c:if>
                <c:out value="${post.postDate}"/>
                <br>
                <div>
                    <a href="${pageContext.request.contextPath}/deletePost?postId=${post.postId}">delete</a>
                    <a href="${pageContext.request.contextPath}/updatePost?postContent=${post.postContent}&postId=${post.postId}">update</a>
                    <a href="${pageContext.request.contextPath}/likePost?postId=${post.postId}">like</a>
                </div>
                <br><br>
            </div>
        </c:forEach>
    </div >
        <div align="center">
            <p><h4>Interesting links</h4></p>
                <ul>
                    <li><a href="http://football.ua/">Football</a> </li>
                    <li><a href="http://google.com/">Google</a> </li>
                    <li><a href="http://HtmlAcademy.ru">HTML</a> </li>
                </ul>
        </div>
    </div>>
</body>
</html>
