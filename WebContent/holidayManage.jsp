<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<style>
#addholidaydiv{
  background-color: #f2f2f2;
}
</style>
</head>
<body>
<h3>Holiday Management Page</h3>
<p>
Message: ${message}
Type: ${type_message}
<br>
SucessMessage: ${sucessMessage }
</p>  

<div id="addholidaydiv">
<form action="holidayRegister" id="addHolidayform" method="post" > 
	<h4>Add New Holiday</h4>
	<table>
	<tr><td>holiday Name:</td><td><input type="text" name="holidayname" required></td></tr>
	<tr><td>Year</td><td><input type="text" name="year" min="4" required></td></tr>
	<tr>
		<td><label>The Starting Date of holiday<input type="date" name="start_date" id="StartDate" required></label></td>
		<td><label>The Ending Date of holiday<input type="date" name="end_date" id="EndDate" required></label></td>
	</tr>
	<tr><td><input type="submit" name="submit" value="addHoliday"></td></tr>
	</table>               
</form>
</div>
<br>
<a href="adminWelcome.jsp">Admin Welcome Page</a>
<a href="index.jsp">logout</a>
</body>
<script>
$("#addHolidayform").validate({
	submitHandler: function(form) {
		var start = new Date($('#StartDate').val());
		var end = new Date($('#EndDate').val());
		if(start<end){
			form.submit();
		}else{
			alert("End date should be larger than start date")
		}
	    
	  }
});
</script>
</html>