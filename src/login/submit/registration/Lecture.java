package login.submit.registration;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.sql.Date;

public class Lecture {

	private String lectureName;
	private int year;
	private Date start;
	private Date end;
	private Time mondayStart;
	private Time mondayEnd;
	private Time TuesdayStart;
	private Time TuesdayEnd;
	private Time WednesdayStart;
	private Time WednesdayEnd;
	private Time ThursdayStart;
	private Time ThursdayEnd;
	private Time FridayStart;
	private Time FridayEnd;
	private Time SaturdayStart;
	private Time SaturdayEnd;
	private Time SundayStart;
	private Time SundayEnd;
	private ArrayList<Time> startTimes;
	private ArrayList<Time> endTimes;
	private ArrayList<String> studentList;
	private ArrayList<Calendar> timeslot; 
	private Attendance attendance = new Attendance();
	
	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	
	public void setAttendance(ArrayList<ArrayList<Entry>> attendance) {
		this.attendance = new Attendance(attendance);
	}
	
	public void setTimeslotformString(String s) throws ParseException {
		timeslot = new ArrayList<>();
		String temp="";
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)!=';'){
				temp+=s.charAt(i);
			}else {
				if(temp!=null) {
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("EEE yyyy-MM-dd HH:mm");
					cal.setTime(sdf.parse(temp));
					timeslot.add(cal);
				}
			}
		}
		System.out.println("setTimeslotformString(): timeslot.size(): "+ timeslot.size());
	}
	
	public void setStudentListformString(String s) throws ParseException {
		studentList = new ArrayList<>();
		String temp="";
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)!=';'){
				temp+=s.charAt(i);
			}else {
				if(temp!=null) {
					studentList.add(temp);
					temp="";
				}
			}
		}
		System.out.println("setStudentListformString(): studentList.size(): "+ studentList.size());
	}
	
	public String timeslottoString() {
		String result = "";
		for(Calendar c: timeslot) {
			SimpleDateFormat format1 = new SimpleDateFormat("EEE yyyy-MM-dd HH:mm");
			String formatted = format1.format(c.getTime());
			result += formatted + ";";
		}
		return result;
	}
	public String studentListtoString() {
		String result = "";
		for(String c: studentList) {
			result += c+ ";";
		}
		return result;
	}
	public ArrayList<Calendar> getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(ArrayList<Calendar> timeslot) {
		this.timeslot = timeslot;
	}
	
	public void addStudent(User u) {
		studentList.add(u.getUsername());
	}
	
	public ArrayList<String> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<String> studentList) {
		this.studentList = studentList;
	}

	public Lecture() {
		timeslot = new ArrayList<>();
		studentList = new ArrayList<>();
		startTimes = new ArrayList<>();
		endTimes = new ArrayList<>();
		startTimes.add(mondayStart);
		startTimes.add(TuesdayStart);
		startTimes.add(WednesdayStart);
		startTimes.add(ThursdayStart);
		startTimes.add(FridayStart);
		startTimes.add(SaturdayStart);
		startTimes.add(SundayStart);
		
		endTimes.add(mondayEnd);
		endTimes.add(TuesdayEnd);
		endTimes.add(WednesdayEnd);
		endTimes.add(ThursdayEnd);
		endTimes.add(FridayEnd);
		endTimes.add(SaturdayEnd);
		endTimes.add(SundayEnd);
		
	}
	
	public ArrayList<Time> getStartTimes() {
		return startTimes;
	}
	public void setStartTimes(ArrayList<Time> startTimes) {
		this.startTimes = startTimes;
	}
	public ArrayList<Time> getEndTimes() {
		return endTimes;
	}
	public void setEndTimes(ArrayList<Time> endTimes) {
		this.endTimes = endTimes;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String Name) {
		lectureName = Name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Time getMondayStart() {
		return mondayStart;
	}
	public void setMondayStart(Time mondayStart) {
		this.mondayStart = mondayStart;
	}
	public Time getMondayEnd() {
		return mondayEnd;
	}
	public void setMondayEnd(Time mondayEnd) {
		this.mondayEnd = mondayEnd;
	}
	public Time getTuesdayStart() {
		return TuesdayStart;
	}
	public void setTuesdayStart(Time tuesdayStart) {
		TuesdayStart = tuesdayStart;
	}
	public Time getTuesdayEnd() {
		return TuesdayEnd;
	}
	public void setTuesdayEnd(Time tuesdayEnd) {
		TuesdayEnd = tuesdayEnd;
	}
	public Time getWednesdayStart() {
		return WednesdayStart;
	}
	public void setWednesdayStart(Time wednesdayStart) {
		WednesdayStart = wednesdayStart;
	}
	public Time getWednesdayEnd() {
		return WednesdayEnd;
	}
	public void setWednesdayEnd(Time wednesdayEnd) {
		WednesdayEnd = wednesdayEnd;
	}
	public Time getThursdayStart() {
		return ThursdayStart;
	}
	public void setThursdayStart(Time thursdayStart) {
		ThursdayStart = thursdayStart;
	}
	public Time getThursdayEnd() {
		return ThursdayEnd;
	}
	public void setThursdayEnd(Time thursdayEnd) {
		ThursdayEnd = thursdayEnd;
	}
	public Time getFridayStart() {
		return FridayStart;
	}
	public void setFridayStart(Time fridayStart) {
		FridayStart = fridayStart;
	}
	public Time getFridayEnd() {
		return FridayEnd;
	}
	public void setFridayEnd(Time fridayEnd) {
		FridayEnd = fridayEnd;
	}
	public Time getSaturdayStart() {
		return SaturdayStart;
	}
	public void setSaturdayStart(Time saturdayStart) {
		SaturdayStart = saturdayStart;
	}
	public Time getSaturdayEnd() {
		return SaturdayEnd;
	}
	public void setSaturdayEnd(Time saturdayEnd) {
		SaturdayEnd = saturdayEnd;
	}
	public Time getSundayStart() {
		return SundayStart;
	}
	public void setSundayStart(Time sundayStart) {
		SundayStart = sundayStart;
	}
	public Time getSundayEnd() {
		return SundayEnd;
	}
	public void setSundayEnd(Time sundayEnd) {
		SundayEnd = sundayEnd;
	}
	
	public void setLectureTime(int DayofWeek, Time start, Time end) {
		//System.out.println("setLectureTime: end: "+ end);
		startTimes.set(DayofWeek, start);
		endTimes.set(DayofWeek, end);
		//System.out.println("setLectureTime: endTimes.get(DayofWeek): "+ endTimes.get(DayofWeek));
	}
	public Time getLectureStartTime(int DayofWeek) {
		return startTimes.get(DayofWeek);
	}
	public Time getLectureEndTime(int DayofWeek) {
		return endTimes.get(DayofWeek);
	}

	
	
}
