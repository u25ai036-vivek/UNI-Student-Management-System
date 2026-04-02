package student;

public interface AuthInterface 
{
	boolean login(String email, String password);
    boolean login(String dept,String email, String password);
    void logout();
}
