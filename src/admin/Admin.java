package admin;

import student.AuthInterface;

public class Admin implements AuthInterface {

    public boolean login(String email, String password) {

        if (email.equals("a") && password.equals("b")) {
            System.out.println("Admin login successful");
            return true;
        } else {
            System.out.println("Invalid credentials");
            return false;
        }
    }

    public void logout() {
        System.out.println("Admin logged out successfully");
    }

	@Override
	public boolean login(String dept, String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}
