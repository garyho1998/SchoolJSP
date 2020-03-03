package login.submit.registration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnectionProvider implements MyProvider {
	static Connection con=null;
	
	public static Connection getCon(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(connUrl,username,pwd);
		}catch(SQLException e) {
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ClassNotFoundException: " + e);
		}
		return con;
	}
}
