<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>

<%--
  Created by IntelliJ IDEA.
  User: OLESIA
  Date: 09.01.2020
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BUY EVERYTHING</title>
</head>
<body>

<h1>BUY EVERYTHING!</h1>

<table border="2">
    <tr>
        <th>ID</th>
        <th>Item</th>
        <th>Price</th>
        <th>Add to bucket</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.id}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="/internetshop2_war_exploded/servlet/bucket?item_id=${item.id}">ADD</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
</form>
<form action="${pageContext.request.contextPath}/index">
    <button>BACK HOME</button>
</form>
</body>
</html>