package login.submit.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO {

	static Connection con;
	static PreparedStatement ps; 	
	
	@Override
	public int insertUser(User u, String type) {
		int status =0;
		try {
			con=MyConnectionProvider.getCon();
			ps=con.prepareStatement("insert into User value(?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, type);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println("Insert fail: " + e);
		}
		return status;
	}

	@Override
	public User getUser(String username, String pass, String type) {
		System.out.println("getUser(): username: "+ username);
		System.out.println("getUser(): password: "+ pass);
		System.out.println("getUser(): type: "+ type);
		
		User u = new User();
		try {
			con=MyConnectionProvider.getCon();
			if(isNullOrEmpty(type) && isNullOrEmpty(pass)){ //check if this user exist 
				System.out.println("getUser(): check if this user exist ");
				ps=con.prepareStatement("select * from User where User=?");
				ps.setString(1, username);
			}else { //check username and password
				System.out.println("getUser(): check username and password ");
				ps=con.prepareStatement("select * from User where User=? and password=?");
				ps.setString(1, username);
				ps.setString(2, pass);
			}

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u.setUsername(rs.getString(1));
				u.setPassword(rs.getString(2));
				u.setType(rs.getString(3));
			}
		}catch(Exception e) {
			System.out.println("select  fail: " + e);
		}
		System.out.println("getUser(): return u ");
		System.out.println("getUser(): u: "+ u);
		System.out.println("getUser(): u.getUsername(): "+ u.getUsername());
		return u;
	}
	
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }
}
