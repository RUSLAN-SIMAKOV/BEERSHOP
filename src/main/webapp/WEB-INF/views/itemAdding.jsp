
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>BEER❤SHOP</title>
    <style type="text/css"><%@include file="../styles/beershop.css"%></style>
</head>
<body>

<form action="${pageContext.request.contextPath}/servlet/itemAdding" method="post">
    <div class="container" class="form-control" align="center">
        <h1>Add item</h1>
        <hr>

        <label for="item_name"><b>Item</b></label>
        <input type="text " placeholder="Enter Item" id="item_name" name="item_name" required>

        <label for="item_price"><b>Price</b></label>
        <input type="number" placeholder="Enter Price" id="item_price" name="item_price" required>

        <button type="submit" class="registerbtn">ADD ITEM</button>
        <hr>
    </div>
</form>
<form action="${pageContext.request.contextPath}/index" class="form-control">
    <button>BACK HOME</button>
</form>
</body>
</html>
