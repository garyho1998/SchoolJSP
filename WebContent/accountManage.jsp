<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
#addUserfrom{
  background-color: #f2f2f2;
}
#addStudentToClassdiv{
	background-color: #f2f2f2;
}
</style>
</head>
<body>
<h3>Account Management Page</h3>
<p>
Message: ${message}
<br>
SucessMessage: ${sucessMessage }
</p>

<div id="addUserfrom">
<form action="accountRegister" method="post" > 
<h4>Add New user</h4>
<table>
<tr><td>UserName:</td><td><input type="text" name="username" min="1" required></td></tr>
<tr><td>Password</td><td><input type="text" name="password" min="1" required></td></tr>
<tr><td>  
	<input type="radio" name="type" value="admin" required> admin<br>
    <input type="radio" name="type" value="teacher"> teacher<br>
  	<input type="radio" name="type" value="student"> student<br>  
 </td></tr>
<tr><td><input type="submit" name="submit" value="addUser"></td></tr>
</table>
</form>
</div>
<br>
<div id="addStudentToClassdiv">
<form action="accountRegister" method="post" > 
	<h4>Add Student to Lecture</h4>
	<table>
	<tr><td>UserName:</td><td><input type="text" name="username" min="1" required></td></tr>
	<tr><td>Lecture Name:</td><td><input type="text" name="lecturename"  required></td></tr>
	<tr><td>Year</td><td><input type="text" name="year" min="4" required></td></tr>
	<tr><td><input type="submit" name="submit" value="addStudent"></td></tr>
	</table>
</form>
</div>
<br>
<a href="adminWelcome.jsp">Admin Welcome Page</a>
<a href="index.jsp">logout</a>
</body>
</html>