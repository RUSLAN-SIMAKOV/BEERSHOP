<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BEER❤SHOP</title>
    <style type="text/css"><%@include file="../styles/bootstrap.min.css"%></style>
    <style type="text/css"><%@include file="../styles/beershop.css"%></style>
</head>
<body>

<h1> Beer Market </h1>

<div class="container col-lg-8"></div>
    <form action="${pageContext.request.contextPath}/registration">
        <button>REGISTRATION NEW USER</button>
    </form>
    <form action="${pageContext.request.contextPath}/login">
        <button>LOGIN</button>
    </form>
    <form action="${pageContext.request.contextPath}/logout">
        <button>LOGOUT</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/purchase">
        <button>START PURCHASING</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/showBucket">
        <button>SHOW BUCKET</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/showOrder">
        <button>SHOW ORDER</button>
    </form>
    <hr>
    <h2>ADMIN ONLY!</h2>
    <hr>
    <form action="${pageContext.request.contextPath}/servlet/getAllUsers">
        <button>SHOW ALL USERS</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/itemAdding">
        <button>ADD NEW ITEM</button>
    </form>
    <form action="${pageContext.request.contextPath}/servlet/getAllItems">
        <button>SHOW ALL ITEMS</button>
    </form>
    <form action="${pageContext.request.contextPath}/test">
        <button>ADD TEST USER AND ADMIN + TEST ITEMS</button>
    </form>
</body>
</html>