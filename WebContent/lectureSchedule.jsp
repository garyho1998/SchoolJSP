<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
#addholidayform{
  background-color: #f2f2f2;
}
#listdiv{
  visibility: ${visibility};
}

</style>
</head>
<body>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Lecture Schedule Page</h3>
<p>
Message: ${message}${type_message}
<br>
SucessMessage: ${sucessMessage }
</p>  

<div>
<form action="lectureRegister" method="post" > 
	<h4>Show Lecture Schedule</h4>
	<table>
		<tr><td>Class Name:</td><td><input type="text" name="lecturename" min="1" required></td></tr>
		<tr><td>Year</td><td><input type="text" name="year" min="4" required></td></tr>
		<tr><td><input type="submit" name="submit" value="showLectureSchedule"></td></tr>       
	</table>    
</form>
<form action="lectureRegister" method="post" > 
<input type="submit" name="submit" value="closeLectureSchedule">
</form>
</div>
<div align="center" id="listdiv">
    <table border="1">
        <tr>
            <th>Lecture Name: ${lecturename}; year: ${year}</th>
        </tr>
		<c:forEach items="${Schedule}" var="Calendar">
		 <tr>
		  <td><c:out value="${Calendar.getTime()}" /></td>
		 </tr>
		</c:forEach>
    </table>
</div>

<br>
<a href="adminWelcome.jsp">adminWelcome</a>
<a href="index.jsp">logout</a>
</body>
</html>