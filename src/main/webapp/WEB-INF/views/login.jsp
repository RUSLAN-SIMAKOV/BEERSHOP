<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>BEER SHOP</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="container">
        <h1>Login</h1>
        <h1>${error}</h1>
        <p style="color: red">Please enter your login and password or press Alt+F4.</p>
        <hr>

        <label for="user_login"><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="user_login" required>

        <label for="user_password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="user_password" required>

        <hr>
        <button type="submit" class="registerbtn">Login</button>
    </div>
</form>
<form action="${pageContext.request.contextPath}/index">
    <button>BACK HOME</button>
</form>
</body>
</html>