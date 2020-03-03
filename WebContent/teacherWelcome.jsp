<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<h3>Welcome</h3>
<form action="lectureRegister" method="post">
<input type="hidden" name="type_message" value="${type_message}">
Hi ${type_message} ${name_message} , You have entered a teacher page

You can do:
<table>
<tr><td><input type="submit" name="submit" value="report_teacher"></td></tr>
<tr><td><input type="submit" name="submit" value="attendance_teacher"></td></tr>

</table>
</form>
</body>
</html>