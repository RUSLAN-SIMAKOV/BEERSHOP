<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>BEER❤SHOP</title>
    <style type="text/css"><%@include file="../styles/bootstrap.min.css"%></style>
    <style type="text/css"><%@include file="../styles/beershop.css"%></style>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <div class="container col-lg-4">

        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="user_login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" class="form-control" id="user_login" name="user_login" required>

        <label for="user_password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" class="form-control" id="user_password" name="user_password" required>

        <label for="user_password_repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password" class="form-control" id="user_password_repeat" name="user_password-repeat" required>

        <label for="user_name"><b>Name</b></label>
        <input type="text" placeholder="Enter Name" class="form-control" id="user_name" name="user_name" required>

        <label for="user_surname"><b>Surname</b></label>
        <input type="text" placeholder="Enter Surname" class="form-control" id="user_surname" name="user_surname" required>

        <hr>
        <button type="submit" class="registerbtn preview-iframe">Register</button>
    </div>
</form>
<form action="${pageContext.request.contextPath}/index">
    <div class="container col-lg-4">
    <button class="bs-footer">BACK HOME</button>
    </div>
</form>
</body>
</html>
