<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<h3>Welcome</h3>

<table>
<tr><td>Hi ${type_message} ${name_message}, You have entered a student page</td></tr>
</table>
You can view your attendance record.

<div id="showLecturediv">
<form action="lectureRegister" method="post" id="showLectureform"> 
	<h4>Show Attendance</h4>
	<table>
		<tr><td><input type="hidden" name="studentName" value="${name_message}"></td></tr>
		<tr><td>Lecture Name:</td><td><input type="text" name="lecturename" min="1" required></td></tr>
		<tr><td>Year</td><td><input type="text" name="year" min="4" required></td></tr>
		<tr><td><input type="submit" name="submit" value="showSelfAttendance"></td></tr>       
	</table>    
</form>
</div>
<br>

<div align="center" id="listdiv">
Lecture Name: ${lecturename}; year: ${year}  
<form action="lectureRegister" method="post">

<input type="hidden" name="lecturename2" value="${lecturename}">
<input type="hidden" name="year2" value="${year}">

<table border="1" id="attendantTable">

<tr><td>Lecture Name</td><td>StudentName</td><td>Rating</td></tr>

<c:forEach items="${RateList}" var="AttendanceRate">
<tr class="tableRow">
<td><c:out value="${AttendanceRate.getLectureName()}" /></td>
<td><c:out value="${AttendanceRate.getStudentName()}" /></td>
<td><c:out value="${AttendanceRate.getRate()}" /></td>

</tr> 
</c:forEach>
</table>
</form>
</div>

<br>
<a href="index.jsp">logout</a>
</body>
</html>