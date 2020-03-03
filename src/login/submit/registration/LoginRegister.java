package login.submit.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginRegister
 */
@WebServlet("/loginRegister")
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO cd = new UserDAOImpl();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String submitType=request.getParameter("submit");
		System.out.println("LoginRegister(): submitType: " + submitType);
		
		User u = cd.getUser(username, password, "");
		
		if(submitType.contentEquals("login") && u!=null && u.getUsername()!=null) {
			 request.setAttribute("name_message", u.getUsername());
			 request.setAttribute("type_message", u.getType());
			 if(u.getType().contentEquals("admin")) {
				 request.getRequestDispatcher("adminWelcome.jsp").forward(request, response);
			 }else if(u.getType().contentEquals("teacher")) {
				 request.getRequestDispatcher("teacherWelcome.jsp").forward(request, response);
			 }else {
				 request.getRequestDispatcher("studentWelcome.jsp").forward(request, response);
			 }
		}else if(submitType.equals("register")){
			User existUser = cd.getUser(username, "", "");
			if(existUser!=null && existUser.getUsername()!=null) {
				request.setAttribute("message", "User already exist! Use other username");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}else{
				u.setUsername(username);
				u.setPassword(password);
				cd.insertUser(u, "student");
				request.setAttribute("message", "");
				request.setAttribute("sucessMessage", "Registration done");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("message", "Data Not Found, click on Register");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
