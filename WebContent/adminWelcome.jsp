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
<form action="loginRegiser" method="post">
<table>
<tr><td>Hi ${type_message} ${name_message} , You have entered a Admin page</td></tr>
</table>
You can do:
<ul>
<li><a href="accountManage.jsp">Account Management</a></li>
<li><a href="lectureManage.jsp">Lecture Management</a></li>
<li><a href="holidayManage.jsp">Holiday Management</a></li>
<li><a href="lectureSchedule.jsp">Lecture Schedule</a></li>
<li><a href="report.jsp">Report</a></li>
<li><a href="attendance.jsp">Attendance Functions</a></li>
</ul>

<a href="index.jsp">logout</a>
</form>
</body>
</html>