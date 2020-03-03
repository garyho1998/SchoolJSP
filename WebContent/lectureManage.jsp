<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>


<meta charset="BIG5">
<title>Insert title here</title>
<style>
#addLecturediv{
  background-color: #f2f2f2;
}
</style>
</head>
<body>
<h3>Lecture Management Page</h3>
<p>
Message: ${message}
<br>
SucessMessage: ${sucessMessage}
</p>

<div id="addLecturediv">
<form action="lectureRegister" id="addLectureform" method="post" > 
	<h4>Add New Lecture</h4>
	<table>
<tr><td>Lecture Name:</td><td><input type="text" name="lecturename"  required></td></tr>
<tr><td>Year</td><td><input type="text" name="year" min="4" required></td></tr>
<tr>
	<td><label>The Starting Date of Lecture<input type="date" name="start_date" id="StartDate" required></label></td>
	<td><label>The Ending Date of Lecture<input type="date" name="end_date" id="EndDate" required ></label></td>
</tr>
<tr>
	<td>Select a Monday Lecture Start time: <input type="time" name="SelectedTime1"></td>
	<td>Select a Monday Lecture End time: <input type="time" name="SelectedTime2"></td>
</tr>
<tr>
	<td>Select a Tuesday Lecture Start time: <input type="time" name="SelectedTime3"></td>
	<td>Select a Tuesday Lecture End time: <input type="time" name="SelectedTime4"></td>
</tr>
<tr>
	<td>Select a Wednesday Lecture Start time: <input type="time" name="SelectedTime5"></td>
	<td>Select a Wednesday Lecture End time: <input type="time" name="SelectedTime6"></td>
</tr>
<tr>
	<td>Select a Thursday Lecture Start time: <input type="time" name="SelectedTime7"></td>
	<td>Select a Thursday Lecture End time: <input type="time" name="SelectedTime8"></td>
</tr>
<tr>
	<td>Select a Friday Lecture Start time: <input type="time" name="SelectedTime9"></td>
	<td>Select a Friday Lecture End time: <input type="time" name="SelectedTime10"></td>
</tr>
<tr>
	<td>Select a Saturday Lecture Start time: <input type="time" name="SelectedTime11"></td>
	<td>Select a Saturday Lecture End time: <input type="time" name="SelectedTime12"></td>
</tr>
<tr>
	<td>Select a Sunday Lecture Start time: <input type="time" name="SelectedTime13"></td>
	<td>Select a Sunday Lecture End time: <input type="time" name="SelectedTime14"></td>
</tr>

<tr><td><input type="submit" name="submit" value="addLecture"></td></tr>
</table>
</form>
</div>
<br>
<a href="adminWelcome.jsp">Admin Welcome Page</a>
<a href="index.jsp">logout</a>
</body>
<script>
$("#addLectureform").validate({
 	submitHandler: function(form) {
 		var flag=1;
 		var start = new Date($('#StartDate').val());
 		var end = new Date($('#EndDate').val());
		
 		if(start>end){
 			flag=0;
 			alert("End date/time should be larger than start date/time");
 		}
		if(flag==1){
 			form.submit();
 		}
}
});
</script>
<script>
</script>
</html>