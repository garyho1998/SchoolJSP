package login.submit.registration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LectureDAOImpl implements LectureDAO {

	static Connection con;
	static PreparedStatement ps; 	
	
	@Override
	public int insertLecture(Lecture l) {
		int status =0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into lecture value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, l.getYear());
			ps.setString(2, l.getLectureName());
			ps.setDate(3, (Date) l.getStart());
			ps.setDate(4, (Date) l.getEnd());
			for(int i=0; i<14; i+=2) {
				if(l.getLectureStartTime(i/2)!=null && l.getLectureEndTime(i/2)!=null) {
					ps.setTime(i+5, (Time) l.getLectureStartTime(i/2));
					ps.setTime(i+6, (Time) l.getLectureEndTime(i/2));
				}else {
					ps.setTime(i+5, (Time) null);
					ps.setTime(i+6, (Time) null);
				}
			}

			ps.setString(19, l.timeslottoString());
			ps.setString(20, l.studentListtoString());
			ps.setString(21, l.getAttendance().AttendancetoString());
			status=ps.executeUpdate();
			System.out.println("insertLecture(): status: "+ status);
			con.close();
		}catch(Exception e) {
			System.out.println("Insert fail: " + e);
		}
		return status;
	}
	
	public int updateLecture(Lecture l, String timeslot, String studentList, String attendance) {
		int status =0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("update lecture set year=?, LectureName=?,"
					+ "Start=?, End=?,"
					+ "mondayStartTime=?, mondayEndTime=?,"
					+ "TuesdayStartTime=?, TuesdayEndTime=?,"
					+ "WednesdayStartTime=?,WednesdayEndTime=?,"
					+ "ThursdayStartTime=?,ThursdayStartTime=?,"
					+ "FridayStartTime=?,FridayStartTime=?,"
					+ "SaturdayStartTime=?,SaturdayStartTime=?,"
					+ "SaturdayStartTime=?,SaturdayStartTime=?,"
					+ "timeslot=?,studentList=?, attendance=?"
					+ "where LectureName=?");
			ps.setInt(1, l.getYear());
			ps.setString(2, l.getLectureName());
			ps.setDate(3, (Date) l.getStart());
			ps.setDate(4, (Date) l.getEnd());
			//System.out.println("updateLecture(): l.getStartTimes().size(): "+ l.getStartTimes().size());
			//System.out.println("insertLecture(): l.getEndTimes().size(): "+ l.getEndTimes().size());
			for(int i=0; i<14; i+=2) {
				if(l.getLectureStartTime(i/2)!=null && l.getLectureEndTime(i/2)!=null) {
					//System.out.println("updateLecture(): l.getLectureStartTime(i/2): "+ l.getLectureStartTime(i/2));
					//System.out.println("insertLecture(): l.getLectureEndTime(i/2): "+ l.getLectureEndTime(i/2));
					ps.setTime(i+5, (Time) l.getLectureStartTime(i/2));
					ps.setTime(i+6, (Time) l.getLectureEndTime(i/2));
				}else {
					//System.out.println("updateLecture(): l.getLectureStartTime(i/2): "+ l.getLectureStartTime(i/2));
					//System.out.println("insertLecture(): l.getLectureEndTime(i/2): "+ l.getLectureEndTime(i/2));
					ps.setTime(i+5, (Time) null);
					ps.setTime(i+6, (Time) null);
				}
			}
			if(!isNullOrEmpty(timeslot)){
				ps.setString(19, timeslot);
			}else {
				ps.setString(19, l.timeslottoString());
			}
			if(!isNullOrEmpty(studentList)){
				ps.setString(20, studentList);
			}else {
				//System.out.println("updateLecture(): l.studentListtoString(): "+ l.studentListtoString());
				ps.setString(20, l.studentListtoString());
			}
			if(!isNullOrEmpty(attendance)){
				//System.out.println("updateLecture(): attendance: "+ attendance);
				ps.setString(21, attendance);
			}else {
				System.out.println("updateLecture(): l.getAttendance().toString(): "+ l.getAttendance().AttendancetoString());
				ps.setString(21, l.getAttendance().AttendancetoString());
			}
			ps.setString(22, l.getLectureName());
			status=ps.executeUpdate();
			System.out.println("updateLecture(): status: "+ status);
			con.close();
		}catch(Exception e) {
			System.out.println("Insert fail: " + e);
		}
		return status;
	}

	@Override
	public Lecture getLecture(String lectureName, int year) {
		System.out.println("getLecture(): lectureName: "+ lectureName);
		System.out.println("getLecture(): year: "+ year);
		
		Lecture l = new Lecture();
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from lecture where lectureName=? and year=?");
			ps.setString(1, lectureName);
			ps.setInt(2, year);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l.setLectureName(rs.getString(2));
				l.setYear(rs.getInt(1));
				l.setStart(rs.getDate(3));
				l.setEnd(rs.getDate(4));
				for(int i=0; i<14; i+=2) {
					Time tempStartTime=rs.getTime(i+5);
					Time tempEndTime=rs.getTime(i+6);
					if(tempStartTime!=null && tempEndTime!=null) {
						//System.out.println("getLecture(): tempStartTime: "+ i+ " " +tempStartTime);
						//System.out.println("getLecture(): tempEndTime: "+ i+ " " +tempEndTime);
						l.setLectureTime(i/2, tempStartTime, tempEndTime);
					}else{
						l.setLectureTime(i/2, null, null);
					}
				}
				System.out.println("  ");
				//System.out.println("getLecture(): rs.getString(21): "+ rs.getString(20));
				//System.out.println("getLecture(): rs.getString(21): "+ rs.getString(21));
				l.setTimeslotformString(rs.getString(19));
				l.setStudentListformString(rs.getString(20));
				l.setAttendance(new Attendance(rs.getString(21)));
			}
		}catch(Exception e) {
			System.out.println("select  fail: " + e);
		}
		System.out.println("getLecture(): l.getLectureName(): "+ l.getLectureName());
		return l;
	}
	
	public ArrayList<Lecture> getAllLecture(int year) {
		System.out.println("getAllLecture(): year: "+ year);
		ArrayList<Lecture> LectureList = new ArrayList<Lecture>();
		
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("select * from lecture where year=?");
			ps.setInt(1, year);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Lecture l = new Lecture();
				l.setLectureName(rs.getString(2));
				l.setYear(rs.getInt(1));
				l.setStart(rs.getDate(3));
				l.setEnd(rs.getDate(4));
				for(int i=0; i<14; i+=2) {
					Time tempStartTime=rs.getTime(i+5);
					Time tempEndTime=rs.getTime(i+6);
					if(tempStartTime!=null && tempEndTime!=null) {
						//System.out.println("getLecture(): tempStartTime: "+ i+ " " +tempStartTime);
						//System.out.println("getLecture(): tempEndTime: "+ i+ " " +tempEndTime);
						l.setLectureTime(i/2, tempStartTime, tempEndTime);
					}else{
						l.setLectureTime(i/2, null, null);
					}
				}
				System.out.println("  ");
				l.setTimeslotformString(rs.getString(19));
				l.setStudentListformString(rs.getString(20));
				l.setAttendance(new Attendance(rs.getString(21)));
				LectureList.add(l);
			}
		}catch(Exception e) {
			System.out.println("select  fail: " + e);
		}
		System.out.println("getLecture(): l.getLectureName(): ");
		return LectureList;
	}
	
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }
}
