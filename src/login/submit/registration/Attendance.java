package login.submit.registration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Attendance {
	private ArrayList<ArrayList<Entry>> attendance = new ArrayList<ArrayList<Entry>>();	
	
	public AttendanceRate getRate(String student){
		float totalClass = 0;
		float attended = 0;
		for(ArrayList<Entry> se : attendance) {
			for(Entry e: se) {
				if(student.contentEquals(e.getStudent())) {
					if(e.isAttendance()) {
						attended++;
					}
					totalClass++;
				}
			}
		}
		AttendanceRate tempRate = new AttendanceRate();
		tempRate.setStudentName(student);
		tempRate.setRate((float) (attended/totalClass));
		
		System.out.println("Attendance(): AttendanceRate: "+ tempRate.getRate() + " " + attended + " " + totalClass);
		return tempRate;
	}
	
	public Attendance(Schedule s, Lecture l){
		ArrayList<String> students = l.getStudentList();
		for(String student : students) {
			ArrayList<Entry> temp = new ArrayList<Entry>();
			for(Calendar c : s.getList()){
				boolean defaultAttendance = false;
				Entry e = new Entry(s.toCalendarString(c),student,defaultAttendance);
				e.setAttendance(checkOriginalAttendance(e, l));
				System.out.println("Attendance(): e.getTimestamp() e.isAttendance(): "+ e.getTimestamp()+" "+e.isAttendance());
				temp.add(e);
			}
			attendance.add(temp);
		}
	}
	
	private boolean checkOriginalAttendance(Entry temp, Lecture l) {
		System.out.println("Attendance(): temp.getTimestamp() temp.isAttendance(): "+ temp.getTimestamp()+" "+temp.isAttendance());
		for(ArrayList<Entry> se : l.getAttendance().getAttendance()) {
			for(Entry e: se) {
				if(e.getTimestamp().contentEquals(temp.getTimestamp())){
					if(e.getStudent().contentEquals(temp.getStudent())){
						System.out.println("Attendance(): e.isAttendance(): "+ e.isAttendance());

						return e.isAttendance();
					}
				}
			}

		}
		return false;
	}

	public Attendance() {}
	
	public Attendance(ArrayList<ArrayList<Entry>> a) {
		this.attendance = a;
	}
	
	public Attendance(String s) {
		parse(s);
	}
	
	public ArrayList<ArrayList<Entry>> changeAttendance(String timestamp, String student, String attended) {
		for(ArrayList<Entry> se : attendance) {
			for(Entry e: se) {
				if(e.getTimestamp().contentEquals(timestamp)){
					if(e.getStudent().contentEquals(student)){
						if(attended.contentEquals("false")) {
							e.setAttendance(true);
						}else {
							e.setAttendance(false);
						}
						return attendance;
						
					}
				}
			}
		}
		return null;
	}
	
	public ArrayList<ArrayList<Entry>> getAttendance() {
		return attendance;
	}

	public String AttendancetoString(){
		String result = "";
		for(ArrayList<Entry> se : attendance) {
			for(Entry e: se) {
				result+=e.getTimestamp()+";"+e.getStudent()+";"+ e.isAttendance()+";";
			}
			result+="@";
			//System.out.println("Attendance(): result: "+ result);
		}
		
		return result;
	}
	
	public void parse(String s){
		//System.out.println("parse(): s"+ s);
		attendance = new ArrayList<ArrayList<Entry>>();
		String tempEntryValue="";
		int semCount=0;
		Entry tempEntry = new Entry();
		ArrayList<Entry> tempSE = new ArrayList<Entry>();
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)==';'){
				if(tempEntryValue!=null) {
					switch(semCount) {
						case 0:
							tempEntry.setTimestamp(tempEntryValue);
							break;
						case 1:
							tempEntry.setStudent(tempEntryValue);
							break;
						case 2:
							tempEntry.setAttendance(Boolean.parseBoolean(tempEntryValue));
							tempSE.add(tempEntry);
							//System.out.println("parse(): Boolean.parseBoolean(tempEntryValue)"+ tempEntryValue + " "+ Boolean.parseBoolean(tempEntryValue));
							semCount=-1;
							tempEntry= new Entry();
							break;
					}	
					semCount++;
					tempEntryValue="";
				}
			}else if(s.charAt(i)=='@') {
				attendance.add(tempSE);
			}else{
				tempEntryValue+=s.charAt(i);
			}
			
		}
		String result = "";
//		for(ArrayList<Entry> se : attendance) {
//			for(Entry e: se) {
//				result+=e.getTimestamp()+";"+e.getStudent()+";"+ e.isAttendance()+";";
//			}
//			result+="@";
//			
//		}
		System.out.println("Attendance(): parse(): result: "+ result);
	}

	public AttendanceRate getRateSmaller(String student) {
		float totalClass = 0;
		float attended = 0;
		for(ArrayList<Entry> se : attendance) {
			for(Entry e: se) {
				if(student.contentEquals(e.getStudent())) {
					if(e.isAttendance()) {
						attended++;
					}
					totalClass++;
				}
			}
		}
		
		AttendanceRate tempRate = new AttendanceRate();
		tempRate.setStudentName(student);
		tempRate.setRate((float) (attended/totalClass));
		
		if(tempRate.getRate()<0.6) {
			return tempRate;
		}else {
			return null;
		}
		
	}
	
	
}
