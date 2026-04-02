package proffesor;

import java.sql.*;

import student.AuthInterface;

public class Professor implements AuthInterface {
	public static String currentProfId;
	public static String dpt;
    public boolean login(String email, String password) { return false; }
    public void logout() {}
	@Override
	public boolean login(String dept, String email, String password) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/your_db";
	    String user = "root";
	    String pass = "mayank09";
	    dpt=dept;
	    try {
	        Connection con = DriverManager.getConnection(url, user, pass);

	        String query = "SELECT id FROM Professor WHERE email=? AND password=?";
	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setString(1, email);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            currentProfId = rs.getString("id");
	            System.out.println("Professor login successful");
	            con.close();
	            return true;
	        }

	        con.close();

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

        return false;
    
	}
}
