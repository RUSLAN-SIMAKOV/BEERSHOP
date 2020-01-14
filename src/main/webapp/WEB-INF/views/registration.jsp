<%--
  Created by IntelliJ IDEA.
  User: OLESIA
  Date: 10.01.2020
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BEER SHOP</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="user_login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="user_login" required>

        <label for="user_password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="user_password" required>

        <label for="user_password-repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password" name="user_password-repeat" required>

        <label for="user_name"><b>Name</b></label>
        <input type="text" placeholder="Enter Name" name="user_name" required>

        <label for="user_surname"><b>Surname</b></label>
        <input type="text" placeholder="Enter Surname" name="user_surname" required>

        <hr>
        <button type="submit" class="registerbtn">Register</button>
    </div>
</form>
<form action="${pageContext.request.contextPath}/index">
    <button>BACK HOME</button>
</form>
</body>
</html>
