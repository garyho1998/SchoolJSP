<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
#showLecturediv{
  background-color: #f2f2f2;
}
#showAllLecturediv{
	background-color: #f2f2f2;
}
#listdiv{
  visibility: ${visibility};
  background-color: #f2f2f2;
}

</style>
</head>
<body>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Attendance Management Page</h3>
<p>
Message: ${message}${type_message}
<br>
SucessMessage: ${sucessMessage}
</p>  

<div id="showLecturediv">
<form action="lectureRegister" method="post" id="showLectureform"> 
<input type="hidden" name="type_message" value="${type_message}">
	<h4>Show Attendance</h4>
	<table>
		<tr><td>Lecture Name:</td><td><input type="text" name="lecturename" min="1" required></td></tr>
		<tr><td>Year</td><td><input type="text" name="year" min="4" required></td></tr>
		<tr><td><input type="submit" name="submit" value="showAttendance"></td></tr>       
	</table>    
</form>
<form action="lectureRegister" method="post" > 
<input type="submit" name="submit" value="closeAttendance">
</form>
</div>
<br>

<div align="center" id="listdiv">
Lecture Name: ${lecturename}; year: ${year}
<form action="lectureRegister" method="post" id="showAttendanceform">
<input type="hidden" name="type_message" value="${type_message}">
<input type="hidden" name="lecturename2" value="${lecturename}">
<input type="hidden" name="A_year" value="${year}">
<table border="1" id="attendantTable">

<tr><td>Lecture Time</td><td>Student</td><td>Attended</td><td>Y for change boolean</td></tr>
<c:forEach items="${Attendance}" var="SE">
<c:forEach items="${SE}" var="Entry">

<tr class="tableRow">
<td><c:out value="${Entry.getTimestamp()}" /></td>
<td><c:out value="${Entry.getStudent()}" /></td>
<td><c:out value="${Entry.isAttendance()}" /></td>
<td>
<input type = "hidden" name="timestamp" value = "${Entry.getTimestamp()}">
<input type = "hidden" name="student" value = "${Entry.getStudent()}">
<input type = "hidden" name="attended" value = "${Entry.isAttendance()}">
<input type="text" name="checker" value="N" class="checker"/>
</td>
</tr> 
</c:forEach>
</c:forEach>
</table>
<input type = "hidden" id = "tableDate">
<input type="submit" id="submit" name="submit" value="changeAttendance">
</form>
</div>

<br>


<br>
<a href="adminWelcome.jsp">Admin Welcome Page</a><br>
<a href="index.jsp">logout</a>
</body>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">


</script>
</html>