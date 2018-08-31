<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My profile</title>
</head>
<body>
<c:if test="${not empty Users}">
<c:forEach items="${names}" var="a">
    ${a} <c:forEach items="${Users}" var="user">
    <c:if test="${user.name eq a}">
        <ul>
        <li>${user.id}</li>
    </ul>
     </c:if>
    </c:forEach>
</c:forEach>
</body>
</html>