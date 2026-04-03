package proffesor;
import java.util.*;
import java.sql.*;


public class ProfService implements ProfOperations  
{
	
	
    public static void assignGrade() {

    	String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
        
        Scanner sc=new Scanner(System.in);
        
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            while (true) {

                System.out.print("Enter Course Code (0 to exit): ");
                String code = sc.nextLine();

                if (code.equals("0")) {
                    con.close();
                    return;
                }

                System.out.print("Enter Class Name: ");
                String className = sc.nextLine();

                String checkCourse = "SELECT * FROM Professor_Course WHERE code=? AND class_name=? AND prof_id=?";
                PreparedStatement ps0 = con.prepareStatement(checkCourse);
                ps0.setString(1, code);
                ps0.setString(2, className);
                ps0.setString(3, Professor.currentProfId);

                ResultSet rs0 = ps0.executeQuery();

                if (!rs0.next()) {
                    System.out.println("You are not assigned to this course or class");
                    continue;
                }

                String listQuery = "SELECT sc.sid, s.name, sc.grade FROM Student_Course sc JOIN Students s ON sc.sid=s.sid WHERE sc.code=? SORT BY sc.sid";
                PreparedStatement psList = con.prepareStatement(listQuery);
                psList.setString(1, code);

                ResultSet rsList = psList.executeQuery();

                boolean found = false;

                while (rsList.next()) {

                    found = true;

                    int sid = rsList.getInt("sid");
                    String name = rsList.getString("name");
                    String currentGrade = rsList.getString("grade");

                    System.out.println("\nStudent: " + sid + " | " + name);

                    System.out.print("Enter New Grade (or press Enter to skip): ");
                    String grade = sc.nextLine();

                    if (grade.equals("")) continue;

                    String update = "UPDATE Student_Course SET grade=? WHERE sid=? AND code=?";
                    PreparedStatement ps2 = con.prepareStatement(update);

                    ps2.setString(1, grade);
                    ps2.setInt(2, sid);
                    ps2.setString(3, code);

                    ps2.executeUpdate();

                    System.out.println("Updated");
                }

                if (!found) {
                    System.out.println("No students enrolled");
                } else {
                    System.out.println("\nAll students processed");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } 
	
	
	public static void listAssignedCourses() {
		
		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
        
        Scanner sc=new Scanner(System.in);
        
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            String query = "SELECT * FROM Professor_Course WHERE prof_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Professor.currentProfId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                    rs.getString("code") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("class_name") +
                    " | Limit: " + rs.getInt("student_limit")
                );
            }

            if (!found) System.out.println("No courses assigned");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	public static void changeSyllabus() {
		
		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
        
        Scanner sc=new Scanner(System.in);
        
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            System.out.print("Course Code: ");
            String code = sc.nextLine();

            String check = "SELECT * FROM Professor_Course WHERE code=? AND prof_id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setString(1, code);
            ps1.setString(2, Professor.currentProfId);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("You cannot modify this course");
                con.close();
                return;
            }

            System.out.print("New Syllabus: ");
            String syllabus = sc.nextLine();

            String update = "UPDATE Courses SET syllabus=? WHERE course_code=?";
            PreparedStatement ps2 = con.prepareStatement(update);

            ps2.setString(1, syllabus);
            ps2.setString(2, code);

            ps2.executeUpdate();

            System.out.println("Syllabus updated");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	public static void changeCredits() {
		
		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
       
        Scanner sc=new Scanner(System.in);
        
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            System.out.print("Course Code: ");
            String code = sc.nextLine();

            String check = "SELECT * FROM Professor_Course WHERE code=? AND prof_id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setString(1, code);
            ps1.setString(2, Professor.currentProfId);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("You cannot modify this course");
                con.close();
                return;
            }

            System.out.print("New Credits: ");
            int credits = sc.nextInt();
            sc.nextLine();

            String update = "UPDATE Courses SET credits=? WHERE course_code=?";
            PreparedStatement ps2 = con.prepareStatement(update);

            ps2.setInt(1, credits);
            ps2.setString(2, code);

            ps2.executeUpdate();

            System.out.println("Credits updated");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	public static void changeTimings() {
		
		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
        
        Scanner sc=new Scanner(System.in);
        
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            System.out.print("Course Code: ");
            String code = sc.nextLine();

            String check = "SELECT * FROM Professor_Course WHERE code=? AND prof_id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setString(1, code);
            ps1.setString(2, Professor.currentProfId);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("You cannot modify this course");
                con.close();
                return;
            }

            System.out.print("New Timing: ");
            String timing = sc.nextLine();

            String update = "UPDATE Schedule SET timing=? WHERE course_code=?";
            PreparedStatement ps2 = con.prepareStatement(update);

            ps2.setString(1, timing);
            ps2.setString(2, code);

            ps2.executeUpdate();

            System.out.println("Timing updated");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	public static void changePrerequisites() {
		
		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
        
        Scanner sc=new Scanner(System.in);
        
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            System.out.print("Course Code: ");
            String code = sc.nextLine();

            String check = "SELECT * FROM Professor_Course WHERE code=? AND prof_id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setString(1, code);
            ps1.setString(2, Professor.currentProfId);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("You cannot modify this course");
                con.close();
                return;
            }

            System.out.print("New Prerequisite: ");
            String pre = sc.nextLine();

            String update = "UPDATE Courses SET prerequisite=? WHERE course_code=?";
            PreparedStatement ps2 = con.prepareStatement(update);

            ps2.setString(1, pre);
            ps2.setString(2, code);

            ps2.executeUpdate();

            System.out.println("Prerequisite updated");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	public static void changeEnrollmentLimit() {
		
		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
        
        Scanner sc=new Scanner(System.in);
        
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            System.out.print("Course Code: ");
            String code = sc.nextLine();

            String check = "SELECT * FROM Professor_Course WHERE code=? AND prof_id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setString(1, code);
            ps1.setString(2, Professor.currentProfId);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("You cannot modify this course");
                con.close();
                return;
            }

            System.out.print("New Limit: ");
            int limit = sc.nextInt();
            sc.nextLine();

            String update = "UPDATE Professor_Course SET student_limit=? WHERE code=?";
            PreparedStatement ps2 = con.prepareStatement(update);

            ps2.setInt(1, limit);
            ps2.setString(2, code);

            ps2.executeUpdate();

            System.out.println("Limit updated");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	public static void listEnrolledStudents() {

		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
        
        Scanner sc=new Scanner(System.in);
        
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            System.out.print("Enter Course Code: ");
            String code = sc.nextLine();

            System.out.print("Enter Class Name: ");
            String className = sc.nextLine();

            String check = "SELECT * FROM Professor_Course WHERE code=? AND class_name=? AND prof_id=?";
            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setString(1, code);
            ps1.setString(2, className);
            ps1.setString(3, Professor.currentProfId);

            ResultSet rs1 = ps1.executeQuery();

            if (!rs1.next()) {
                System.out.println("You cannot view this course or class");
                con.close();
                return;
            }

            String query =
                "SELECT sc.sid, s.name, s.contact, s.cgpa, sc.grade, sc.course_regt_date " +
                "FROM Student_Course sc " +
                "JOIN Students s ON sc.sid = s.sid " +
                "WHERE sc.code = ? AND s.class_name = ?";

            PreparedStatement ps2 = con.prepareStatement(query);
            ps2.setString(1, code);
            ps2.setString(2, className);

            ResultSet rs = ps2.executeQuery();

            boolean found = false;

            System.out.println("\n--- Enrolled Students ---");

            while (rs.next()) {
                found = true;

                System.out.println(
                    rs.getInt("sid") + " | " +
                    rs.getString("name") + " | Contact: " +
                    rs.getString("contact") + " | CGPA: " +
                    rs.getFloat("cgpa") + " | Grade: " +
                    rs.getString("grade") + " | Registered: " +
                    rs.getDate("course_regt_date")
                );
            }

            if (!found) {
                System.out.println("No students enrolled in this class");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	public static void viewSchedule()
	{
		
		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
        
        Scanner sc=new Scanner(System.in);

		    try {
		        Connection con = DriverManager.getConnection(url, user, pass);

		        String query =
		            "SELECT s.day, s.timing, s.location, s.course_code, pc.class_name " +
		            "FROM Schedule s " +
		            "JOIN Professor_Course pc ON s.course_code = pc.code " +
		            "WHERE pc.prof_id=?";

		        PreparedStatement ps = con.prepareStatement(query);
		        ps.setString(1, Professor.currentProfId);

		        ResultSet rs = ps.executeQuery();

		        Map<String, List<String>> scheduleMap = new LinkedHashMap<>();

		        while (rs.next()) {

		            String day = rs.getString("day");

		            String entry =
		                rs.getString("course_code") +
		                "(" + rs.getString("class_name") + ")" +
		                "[" + rs.getString("timing") +
		                " | " + rs.getString("location") + "]";

		            if (!scheduleMap.containsKey(day)) {
		                scheduleMap.put(day, new ArrayList<>());
		            }

		            scheduleMap.get(day).add(entry);
		        }

		        if (scheduleMap.isEmpty()) {
		            System.out.println("No schedule assigned");
		            return;
		        }

		        System.out.println("\n--- Professor Weekly Schedule ---\n");

		        for (String day : scheduleMap.keySet()) {

		            System.out.print(day + "    ");

		            for (String entry : scheduleMap.get(day)) {
		                System.out.print(entry + "    ");
		            }

		            System.out.println();
		        }

		        con.close();

		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		    }
		}
	
		
	public void changePassword() {
		
		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
        
        Scanner sc=new Scanner(System.in);

	    try {
	        Connection con = DriverManager.getConnection(url, user, pass);

	   

	        String pid = Professor.currentProfId;

	        System.out.print("Enter Old Password: ");
	        String oldPass = sc.nextLine();

	        String check = "SELECT password FROM Professor WHERE id=?";
	        PreparedStatement ps1 = con.prepareStatement(check);
	        ps1.setString(1, pid);

	        ResultSet rs = ps1.executeQuery();

	        if (!rs.next()) {
	            System.out.println("Professor not found");
	            return;
	        }

	        String actualPass = rs.getString("password");

	        if (!actualPass.equals(oldPass)) {
	            System.out.println("Incorrect old password");
	            return;
	        }

	        System.out.print("Enter New Password: ");
	        String newPass = sc.nextLine();

	        System.out.print("Confirm New Password: ");
	        String confirmPass = sc.nextLine();

	        if (newPass.equals(oldPass)) {
	            System.out.println("New password cannot be same as old password");
	            return;
	        }

	        if (!newPass.equals(confirmPass)) {
	            System.out.println("Passwords do not match");
	            return;
	        }

	        if (newPass.length() < 6) {
	            System.out.println("Password must be at least 6 characters");
	            return;
	        }

	        String update = "UPDATE Professor SET password=? WHERE id=?";
	        PreparedStatement ps2 = con.prepareStatement(update);

	        ps2.setString(1, newPass);
	        ps2.setString(2, pid);

	        ps2.executeUpdate();

	        System.out.println("Password updated successfully");

	        con.close();

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}
	
}
	

