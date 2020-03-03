<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
#showRatediv{
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
<h3>Report Page</h3>
<p>
Message: ${message}
<br>
Type: ${type_message}
<br>
SucessMessage: ${sucessMessage}
</p>  

<div id="showRatediv">
<form action="lectureRegister" method="post" id="showRateform"> 
	<h4>Show Attendance Ratio by Lecture</h4>
	<table>
		<tr><td>Year</td><td><input type="text" name="year" min="4" required></td></tr>
		<tr><td><input type="submit" name="submit" value="showRate"></td></tr>   
		<tr><td><input type="submit" name="submit" value="showRate(<60%)"></td></tr>       
	</table>    
</form>
<form action="lectureRegister" method="post" > 
</form>
</div>
<br>
<div align="center" id="listdiv">
year: ${year}

<c:forEach items="${RateListbyLecture.entrySet()}" var="RateList">
	Lecture(${RateList.getKey()})
	<table border="1">
	<tr><td>Student</td><td>Ratio(Attended/Total)</td></tr>
	<c:forEach items="${RateList.getValue()}" var="AttendanceRate">
	<tr class="tableRow">
		<td><c:out value="${AttendanceRate.getStudentName()}" /></td>
		<td><c:out value="${AttendanceRate.getRate()}" /></td>
	</tr>
	</c:forEach>
	</table>
	<br>
</c:forEach>
</div>

<br>


<br>
<a href="adminWelcome.jsp">adminWelcome</a>
<a href="index.jsp">logout</a>
</body>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">


</script>
</html>