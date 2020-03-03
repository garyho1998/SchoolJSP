package login.submit.registration;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginRegister
 */
@WebServlet("/lectureRegister")
public class LectureRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LectureRegister() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String lecturename=request.getParameter("lecturename");
		int year = 0;
		if(request.getParameter("year")!=null) {
			year=Integer.parseInt(request.getParameter("year"));
		}
		String submitType=request.getParameter("submit");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		ArrayList<Time> TimeList = new ArrayList<>();
		for(int i=1; i<15; i+=2) {
			String startTimeString = request.getParameter("SelectedTime"+Integer.toString(i));
			String endTimeString = request.getParameter("SelectedTime"+Integer.toString(i+1));
			if(!isNullOrEmpty(startTimeString)  && !isNullOrEmpty(startTimeString)) {
				startTimeString+=":00";
				endTimeString+=":00";
				System.out.println("lectureRegister(): startTimeString: "+ startTimeString);
				//System.out.println("lectureRegister(): endTimeString: "+ endTimeString);
			    TimeList.add(Time.valueOf(startTimeString));
				TimeList.add(Time.valueOf(endTimeString));
			}else {
				TimeList.add(null);
				TimeList.add(null);
			}
		}
		
		LectureDAO cd = new LectureDAOImpl();
		Lecture l = cd.getLecture(lecturename, year);
		HolidayDAO hd = new HolidayDAOImpl();
		ArrayList<Holiday> holidayList = hd.getAllHoliday(year);
		
		if(submitType.contentEquals("closeLectureSchedule")) {
			request.setAttribute("visibility", "hidden");
			request.setAttribute("message", "Lecture Schedule closed");
			request.getRequestDispatcher("lectureSchedule.jsp").forward(request, response);
		}else if(submitType.contentEquals("closeAttendance")) {
			request.setAttribute("visibility", "hidden");
			request.setAttribute("message", "Attendance closed");
			request.getRequestDispatcher("attendance.jsp").forward(request, response);
		}else if(submitType.contentEquals("report_teacher")) {
			String type_message=request.getParameter("type_message");
			request.setAttribute("visibility", "hidden");
			request.setAttribute("type_message", type_message);
			request.setAttribute("message", "Attendance closed");
			request.getRequestDispatcher("report_teacher.jsp").forward(request, response);
		}else if(submitType.contentEquals("goTeacherWelcome")) {
			String type_message=request.getParameter("type_message");
			request.setAttribute("visibility", "hidden");
			request.setAttribute("type_message", type_message);
			request.setAttribute("message", "Attendance closed");
			request.getRequestDispatcher("teacherWelcome.jsp").forward(request, response);
		}else if(submitType.contentEquals("attendance_teacher")) {
			String type_message=request.getParameter("type_message");
			request.setAttribute("visibility", "hidden");
			request.setAttribute("type_message", type_message);
			request.setAttribute("message", "Attendance closed");
			request.getRequestDispatcher("attendance_teacher.jsp").forward(request, response);
		}else if(submitType.contentEquals("addLecture") ) {
			if(l!=null && l.getLectureName()!=null) {
				 request.setAttribute("name_message", l.getLectureName());
				 request.setAttribute("message", "Lecture already exist! Use other Lecture Name");
				 request.getRequestDispatcher("lectureManage.jsp").forward(request, response);
			}else {
				l.setLectureName(lecturename);
				l.setYear(year);
				l.setStart(Date.valueOf(start_date)); 
				l.setEnd(Date.valueOf(end_date)); 
				for(int i=0; i<14; i+=2) {
					if(TimeList.get(i)!=null && TimeList.get(i+1)!=null) {
						l.setLectureTime((i)/2, TimeList.get(i), TimeList.get(i+1));
					}else{
						l.setLectureTime((i)/2, null, null);
					}
				}
				//System.out.println("lectureRegister(): l.getStartTimes().size(): "+ l.getStartTimes().size());
				Schedule s = new Schedule(l,holidayList);
				Attendance attendance = new Attendance(s, l);
				l.setTimeslot(s.getList());
				l.setAttendance(attendance);
				cd.insertLecture(l);
				
				request.setAttribute("message", "");
				request.setAttribute("sucessMessage", "Added Lecture");
				request.getRequestDispatcher("lectureManage.jsp").forward(request, response);
			}

		}else if(submitType.contentEquals("showLectureSchedule")) {
			if(l.getLectureName()==null) {
				request.setAttribute("visibility", "hidden");
				request.setAttribute("message", "Lecture dont exist");
				request.getRequestDispatcher("lectureSchedule.jsp").forward(request, response);
			}else {
				Schedule s = new Schedule(l,holidayList);
				
				cd.updateLecture(l, s.toString(),"","");
				
				System.out.println("lectureRegister(): l.getLectureName(): "+ l.getLectureName());
				System.out.println("lectureRegister(): s.getList().size(): "+ s.getList().size());
				
				if(s.getList().size()!=0) {
					request.setAttribute("message", "");
					request.setAttribute("lecturename", lecturename);
					request.setAttribute("Schedule", s.getList());
					request.setAttribute("year", year);
					request.setAttribute("sucessMessage", "Schedule is showed below");
					request.setAttribute("visibility", "visible");
				}else {
					request.setAttribute("visibility", "hidden");
					request.setAttribute("message", "No time slot for this lecture (may be overlaped with holidays)");
				}
				
				request.getRequestDispatcher("lectureSchedule.jsp").forward(request, response);
			}

		}else if(submitType.contentEquals("showAttendance")){
			String type_message=request.getParameter("type_message");
			if(l.getLectureName()==null) {
				request.setAttribute("visibility", "hidden");
				request.setAttribute("message", "Lecture dont exist");
				if(isNullOrEmpty(type_message)) {
					request.getRequestDispatcher("attendance.jsp").forward(request, response);
				}else{
					if(type_message.contentEquals("teacher")) {
						request.getRequestDispatcher("attendance_teacher.jsp").forward(request, response);
					}else {
						request.getRequestDispatcher("attendance.jsp").forward(request, response);
					}
				}
			}else{		
				Schedule s = new Schedule(l,holidayList);
				Attendance attendance = new Attendance(s, l);
				
				cd.updateLecture(l, s.toString(),"",attendance.AttendancetoString());
				
				if(s.getList().size()!=0) {
					request.setAttribute("message", "");
					request.setAttribute("lecturename", lecturename);
					request.setAttribute("Attendance", attendance.getAttendance());
					request.setAttribute("year", year);
					request.setAttribute("sucessMessage", "Attendance is showed below");
					request.setAttribute("visibility", "visible");
				}else {
					request.setAttribute("visibility", "hidden");
					request.setAttribute("message", "No time slot for this lecture (may be overlaped with holidays)");
				}
				if(isNullOrEmpty(type_message)) {
					request.getRequestDispatcher("attendance.jsp").forward(request, response);
				}else{
					if(type_message.contentEquals("teacher")) {
						request.getRequestDispatcher("attendance_teacher.jsp").forward(request, response);
					}else {
						request.getRequestDispatcher("attendance.jsp").forward(request, response);
					}
				}
			}
		}else if(submitType.contentEquals("showSelfAttendance")){
			if(l.getLectureName()==null) {
				request.setAttribute("visibility", "hidden");
				request.setAttribute("message", "Lecture dont exist");
				request.getRequestDispatcher("studentWelcome.jsp").forward(request, response);
			}else{	
				String student = request.getParameter("studentName");
				if(isNullOrEmpty(lecturename)){
					lecturename = request.getParameter("lecturename2");
					String year2_str = request.getParameter("year2");
					year = Integer.parseInt(year2_str);
				}
				
				ArrayList<Lecture> LectureList = cd.getAllLecture(year);
				ArrayList<AttendanceRate> RateList = new ArrayList<AttendanceRate>();
				for(Lecture lec: LectureList) {
					Schedule s = new Schedule(lec,holidayList);
					Attendance attendance = new Attendance(s, lec);
					AttendanceRate AR = attendance.getRate(student);
					AR.setLectureName(lec.getLectureName());
					RateList.add(AR);
				}
				request.setAttribute("message", "");
				request.setAttribute("RateList", RateList);
				request.setAttribute("year", year);
				request.setAttribute("sucessMessage", "Attendance is showed below");
				request.setAttribute("visibility", "visible");
				request.getRequestDispatcher("studentWelcome.jsp").forward(request, response);
			}
			}else if(submitType.contentEquals("changeAttendance")){
				String type_message=request.getParameter("type_message");
				String lecturename2=request.getParameter("lecturename2");
				int year2=0;
				if(request.getParameter("A_year")!=null) {
					year2=Integer.parseInt(request.getParameter("A_year"));
				}
				String timestamp[]=request.getParameterValues("timestamp");
				String student[]=request.getParameterValues("student");
				String attended[]=request.getParameterValues("attended");
				String checker[]=request.getParameterValues("checker");
				
				Lecture l2 = cd.getLecture(lecturename2, year2);
				Schedule s = new Schedule(l2,holidayList);
				Attendance attendance = new Attendance(s, l2);
				for(int i=0; i<timestamp.length; i++) {
					if(!isNullOrEmpty(checker[i])) {
						if(checker[i].contentEquals("Y")) {
							l2.setAttendance(attendance.changeAttendance(timestamp[i], student[i], attended[i]));
						}		
					}
				}
				cd.updateLecture(l2, s.toString(),"",l2.getAttendance().AttendancetoString());

				request.setAttribute("message", "");
				request.setAttribute("lecturename", lecturename2);
				request.setAttribute("Attendance", l2.getAttendance().getAttendance());
				request.setAttribute("year", year2);
				request.setAttribute("sucessMessage", "Attendance is showed below");
				request.setAttribute("visibility", "visible");
				if(isNullOrEmpty(type_message)) {
					request.getRequestDispatcher("attendance.jsp").forward(request, response);
				}else{
					if(type_message.contentEquals("teacher")) {
						request.getRequestDispatcher("attendance_teacher.jsp").forward(request, response);
					}else {
						request.getRequestDispatcher("attendance.jsp").forward(request, response);
					}
				}
		}else if(submitType.contentEquals("showRate")){
			String type_message=request.getParameter("type_message");
			year=0;
			if(request.getParameter("year")!=null) {
				year=Integer.parseInt(request.getParameter("year"));
			}
			
			
			ArrayList<Lecture> LectureList = cd.getAllLecture(year);
			HashMap<String, ArrayList<AttendanceRate>> RateListbyLecture = new HashMap<String, ArrayList<AttendanceRate>>();
			for(Lecture lec: LectureList) {
				ArrayList<AttendanceRate> tempRateList = new ArrayList<AttendanceRate>();
				Schedule s = new Schedule(lec,holidayList);
				Attendance attendance = new Attendance(s, lec);
				for(String student : lec.getStudentList()) {
					AttendanceRate AR = attendance.getRate(student);
					tempRateList.add(AR);
				}
				RateListbyLecture.put(lec.getLectureName(), tempRateList);
			}
			request.setAttribute("message", "");
			request.setAttribute("type_message", type_message);
			request.setAttribute("RateListbyLecture", RateListbyLecture);
			request.setAttribute("year", year);
			request.setAttribute("sucessMessage", "Attendance is showed below");
			request.setAttribute("visibility", "visible");
			if(isNullOrEmpty(type_message)) {
				request.getRequestDispatcher("report.jsp").forward(request, response);
			}else{
				if(type_message.contentEquals("teacher")) {
					request.getRequestDispatcher("report_teacher.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("report.jsp").forward(request, response);
				}
			}
			
	
		}else if(submitType.contentEquals("showRate(<60%)")){
			String type_message=request.getParameter("type_message");
			year=0;
			if(request.getParameter("year")!=null) {
				year=Integer.parseInt(request.getParameter("year"));
			}
			ArrayList<Lecture> LectureList = cd.getAllLecture(year);
			HashMap<String, ArrayList<AttendanceRate>> RateListbyLecture = new HashMap<String, ArrayList<AttendanceRate>>();
			for(Lecture lec: LectureList) {
				ArrayList<AttendanceRate> tempRateList = new ArrayList<AttendanceRate>();
				Schedule s = new Schedule(lec,holidayList);
				Attendance attendance = new Attendance(s, lec);
				for(String student : lec.getStudentList()) {
					AttendanceRate AR = attendance.getRateSmaller(student);
					if(AR!=null) {
						tempRateList.add(AR);
					}  
				}
				RateListbyLecture.put(lec.getLectureName(), tempRateList);
			}
			request.setAttribute("message", "");
			request.setAttribute("type_message", type_message);
			request.setAttribute("RateListbyLecture", RateListbyLecture);
			request.setAttribute("year", year);
			request.setAttribute("sucessMessage", "Attendance is showed below");
			request.setAttribute("visibility", "visible");
			if(isNullOrEmpty(type_message)) {
				request.getRequestDispatcher("report.jsp").forward(request, response);
			}else{
				if(type_message.contentEquals("teacher")) {
					request.getRequestDispatcher("report_teacher.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("report.jsp").forward(request, response);
				}
			}
	
		}else {  
				request.setAttribute("message", "Unknow Error in lecture Register doPost");
				request.getRequestDispatcher("lectureManage.jsp").forward(request, response);
		}
	}
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;   
        return true;
    }
}
