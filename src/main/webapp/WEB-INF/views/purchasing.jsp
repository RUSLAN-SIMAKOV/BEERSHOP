<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:useBean id="items" scope="request" type="java.util.List<beershop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BEER❤SHOP</title>
    <style type="text/css"><%@include file="../styles/beershop.css"%></style>
</head>
<body>

<h1>BUY EVERYTHING!</h1>

<div align="center">
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
                <a href="/beershop_war_exploded/servlet/bucket?item_id=${item.id}">ADD</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
</form>
<form action="${pageContext.request.contextPath}/index" class="form-control">
    <button>BACK HOME</button>
</form>
</div>
</body>
</html>