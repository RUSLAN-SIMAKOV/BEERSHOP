<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="users" scope="request" type="java.util.List<beershop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BEER❤SHOP</title>
    <style type="text/css">
        <%@include file="../styles/beershop.css" %>
    </style>
</head>
<body>

<h1>All Users, ${greeting}!</h1>

<div align="center">
    <table border="2" bgcolor="white">
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Rolex</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>
                    <c:out value="${user.surname}"/>
                </td>
                <td>
                    <a href="/beershop_war_exploded/servlet/deleteUser?user_id=${user.id}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="${pageContext.request.contextPath}/registration" class="form-control">
        <button>ADD NEW USER</button>
    </form>
    <form action="${pageContext.request.contextPath}/index" class="form-control">
        <button>BACK HOME</button>
    </form>
</div>
</body>
</html>
