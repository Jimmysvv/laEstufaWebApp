<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>

<c:set var="user" value="${(empty user) ? 'World' : user}" />
<h2>
    <%--Hello ${user}!--%>
</h2>

<jsp:include page="footer.jsp"/>
