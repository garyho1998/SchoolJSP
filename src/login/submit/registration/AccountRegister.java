package login.submit.registration;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginRegister
 */
@WebServlet("/accountRegister")
public class AccountRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AccountRegister() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String type=request.getParameter("type");
		String submitType=request.getParameter("submit");
		
		System.out.println("accountRegister(): username: "+ username);
		System.out.println("accountRegister(): password: "+ password);
		System.out.println("accountRegister(): type: "+ type);
		System.out.println("accountRegister(): submitType: "+ submitType);
		
		UserDAO cd = new UserDAOImpl();
		User u = cd.getUser(username, "", "");

		if(submitType.contentEquals("addUser")) {
			if(u!=null && u.getUsername()!=null){
				request.setAttribute("name_message", u.getUsername());
				request.setAttribute("type_message", u.getType());
				request.setAttribute("message", "");
				request.setAttribute("message", "User already exist! Use other username");
				request.getRequestDispatcher("accountManage.jsp").forward(request, response);
			}else {
				u.setUsername(username);
				u.setPassword(password);
				cd.insertUser(u, type);
				request.setAttribute("message", "");
				request.setAttribute("sucessMessage", "Added user("+username+")");
				request.getRequestDispatcher("accountManage.jsp").forward(request, response);
			}
		}else if(submitType.contentEquals("addStudent")) {
			if(u!=null && u.getUsername()!=null) {
				LectureDAO ld = new LectureDAOImpl();
				String lecturename=request.getParameter("lecturename");
				int year=Integer.parseInt(request.getParameter("year"));
				Lecture l = ld.getLecture(lecturename, year);
				
				if(l!=null && l.getLectureName()!=null) {
					if(l.getStudentList().contains(username)) {
						request.setAttribute("message", "User already in lecture" );
						request.getRequestDispatcher("accountManage.jsp").forward(request, response);
					}else {
						l.addStudent(u);
						System.out.println("accountRegister(): l.getLectureName(): "+ l.getLectureName());
						
						HolidayDAO hd = new HolidayDAOImpl();
						ArrayList<Holiday> holidayList = hd.getAllHoliday(year);
						Schedule s = new Schedule(l,holidayList);
						Attendance attendance = new Attendance(s, l);
						ld.updateLecture(l, s.toString(),l.studentListtoString(),attendance.toString());
						request.setAttribute("sucessMessage", "Added User("+ u.getUsername() + ") to lecture("+ l.getLectureName()+")" );
						request.getRequestDispatcher("accountManage.jsp").forward(request, response);
					}
				}else {
					request.setAttribute("message", "Lecture dont exist");
					request.getRequestDispatcher("accountManage.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("message", "User dont exist");
				request.getRequestDispatcher("accountManage.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("message", "Data Not Found, click on Register");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
