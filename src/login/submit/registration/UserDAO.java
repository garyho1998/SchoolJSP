package login.submit.registration;

public interface UserDAO {
	public User getUser(String username, String pass, String type) ;
	public int insertUser(User s, String type);
}
