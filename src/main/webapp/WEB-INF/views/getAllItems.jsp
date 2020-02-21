<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="items" scope="request" type="java.util.List<beershop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BEER❤SHOP</title>
    <style type="text/css">
        <%@include file="../styles/beershop.css" %>
    </style>
</head>
<body>

<h1>All Items!</h1>
<div align="center">
    <table border="2" bgcolor="white">
        <tr>
            <th>ID</th>
            <th>Item</th>
            <th>Price</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>
                    <c:out value="${item.id}"/>
                </td>
                <td>
                    <c:out value="${item.name}"/>
                </td>
                <td>
                    <c:out value="${item.price}"/>
                </td>
                <td>
                    <a href="/beershop_war_exploded/servlet/deleteItem?item_id=${item.id}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="${pageContext.request.contextPath}/servlet/itemAdding" class="form-control">
        <button>ADD NEW ITEM</button>
    </form>
    <form action="${pageContext.request.contextPath}/index" class="form-control">
        <button>BACK HOME</button>
    </form>
</div>
</body>
</html>
