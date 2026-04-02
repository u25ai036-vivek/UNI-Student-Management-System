package student;


import java.sql.*;

public class Student implements AuthInterface {

	public static String currentStudentId;
	public static String dpt;
    

    public boolean login(String dept,String email, String password) {
    	dpt=dept;
    	String url = "jdbc:mysql://localhost:3306/"+dept;
        String user = "root";
        String pass = "mayank09";
        try {
	        Connection con = DriverManager.getConnection(url, user, pass);

	        String query = "SELECT id FROM Student WHERE email=? AND password=?";
	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, email);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            currentStudentId = rs.getString("id");
	            System.out.println("Student login successful");
	            con.close();
	            return true;
	        }

	        con.close();

	    } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public void logout() {
        System.out.println("Student logged out successfully");
    }

	@Override
	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}
