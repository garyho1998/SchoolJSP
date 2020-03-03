<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<h3>Login Page</h3>
<form action="loginRegister" method="post">
<table>
<tr><td>Message:${message }</td></tr>
<tr><td>SucessMessage:${sucessMessage }</td></tr>
<tr><td>UserName:</td><td><input type="text" name="username" min="1" required></td></tr>
<tr><td>Password</td><td><input type="text" name="password" min="1" required></td></tr>
<tr><td><input type="submit" name="submit" value="login"></td><td><a href="register.jsp">Registration</a></td></tr>
</table>
</form>
</body>
</html>