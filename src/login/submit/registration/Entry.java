package login.submit.registration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Entry {
	private String timestamp;
	private String student;
	private boolean attendance;
	
	public Entry() {
		
	}
	public Entry(String t, String s, boolean a) {
		this.timestamp = t;
		this.student = s;
		this.attendance = a;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public boolean isAttendance() {
		return attendance;
	}
	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}
	

	
	
}
