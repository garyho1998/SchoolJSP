package login.submit.registration;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginRegister
 */
@WebServlet("/holidayRegister")
public class HolidayRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public HolidayRegister() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HolidayDAO cd = new HolidayDAOImpl();
		
		String holidayname=request.getParameter("holidayname");
		int year=Integer.parseInt(request.getParameter("year"));
		String submitType=request.getParameter("submit");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		
		System.out.println("holidayRegister(): holidayname: "+ holidayname);
		System.out.println("holidayRegister(): year: "+ year);
		System.out.println("holidayRegister(): start_date: "+ start_date);
		System.out.println("holidayRegister(): submitType: "+ submitType);
		
		Holiday h = cd.getHoliday(holidayname, year );

		if(submitType.contentEquals("addHoliday") && h!=null && h.getHolidayName()!=null) {
			 request.setAttribute("name_message", h.getHolidayName());
			 request.setAttribute("message", "");
			 request.setAttribute("message", "holiday already exist! Use other holiday Name");
			 request.getRequestDispatcher("holidayManage.jsp").forward(request, response);
		}else if(submitType.equals("addHoliday")){
			h.setHolidayName(holidayname);
			h.setYear(year);
			h.setStart(Date.valueOf(start_date)); 
			h.setEnd(Date.valueOf(end_date)); 
			cd.insertHoliday(h);
			request.setAttribute("message", "");
			request.setAttribute("sucessMessage", "Added holiday");
			request.getRequestDispatcher("holidayManage.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "Unknow Error in holiday Register doPost");
			request.getRequestDispatcher("holidayManage.jsp").forward(request, response);
		}
	}
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;   
        return true;
    }
}
