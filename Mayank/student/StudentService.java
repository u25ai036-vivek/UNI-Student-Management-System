package student;
import java.util.*;

import proffesor.Professor;

import java.sql.*;
import java.sql.Date;


public interface StudentService {

	public static void viewAvailableCourses() {
		
		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);
		 try {
		        Connection con = DriverManager.getConnection(url, user, pass);

		        String sid = Student.currentStudentId;

		        String semQuery = "SELECT semster FROM Students WHERE sid=?";
		        PreparedStatement ps1 = con.prepareStatement(semQuery);
		        ps1.setString(1, sid);

		        ResultSet rs1 = ps1.executeQuery();

		        if (!rs1.next()) {
		            System.out.println("Student not found");
		            return;
		        }

		        int sem = rs1.getInt("semster");

		        String query = "SELECT * FROM Courses WHERE semester=?";
		        PreparedStatement ps2 = con.prepareStatement(query);
		        ps2.setInt(1, sem);

		        ResultSet rs = ps2.executeQuery();

		        boolean found = false;

		        System.out.println("\n--- Available Courses (Semester " + sem + ") ---");

		        while (rs.next()) {

		            found = true;

		            System.out.println("\n--------------------------------------");

		            System.out.println("Course Code: " + rs.getString("course_code"));
		            System.out.println("Title: " + rs.getString("title"));
		            System.out.println("Credits: " + rs.getInt("credits"));
		            System.out.println("Prerequisite: " + rs.getString("prerequisite"));
		            System.out.println("Semester: " + rs.getString("semester"));
		            System.out.println("Course Type: " + rs.getString("course_type"));
		            System.out.println("Syllabus: " + rs.getString("syllabus"));
		        }

		        if (!found) {
		            System.out.println("No courses available for your semester");
		        }

		        System.out.println("\n--------------------------------------");

		        con.close();

		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		    }
	}

	public static void registerCourse() {

		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);
		
		try {
	        Connection con = DriverManager.getConnection(url, user, pass);

	        String sid = Student.currentStudentId;

	        String semQuery = "SELECT semster, sem_credit FROM Students WHERE sid=?";
	        PreparedStatement ps0 = con.prepareStatement(semQuery);
	        ps0.setString(1, sid);

	        ResultSet rs0 = ps0.executeQuery();

	        if (!rs0.next()) {
	            System.out.println("Student not found");
	            return;
	        }

	        int currentSem = rs0.getInt("semster");
	        int semCredit = rs0.getInt("sem_credit");

	        String gradeCheck = "SELECT * FROM Student_Course WHERE sid=? AND grade IS NULL";
	        PreparedStatement ps1 = con.prepareStatement(gradeCheck);
	        ps1.setString(1, sid);

	        ResultSet rs1 = ps1.executeQuery();

	        if (!rs1.next()) {

	            String promote =
	                "UPDATE Students SET semster=semster+1, total_credit = total_credit + sem_credit, sem_credit=0 WHERE sid=?";
	            PreparedStatement psPromote = con.prepareStatement(promote);
	            psPromote.setString(1, sid);
	            psPromote.executeUpdate();

	            currentSem++;
	            semCredit = 0;

	            System.out.println("Promoted to Semester: " + currentSem);
	        }

	        while (true) {

	            System.out.println("\n--- Available Courses ---");

	            String courseQuery = "SELECT * FROM Courses WHERE semester=?";
	            PreparedStatement ps2 = con.prepareStatement(courseQuery);
	            ps2.setInt(1, currentSem);

	            ResultSet rs = ps2.executeQuery();

	            while (rs.next()) {
	                System.out.println(
	                    rs.getString("course_code") + " | " +
	                    rs.getString("title") + " | " +
	                    rs.getInt("credits") + " | " +
	                    rs.getString("course_type")
	                );
	            }

	            System.out.print("\nEnter Course Code (0 to stop): ");
	            String code = sc.nextLine();

	            if (code.equals("0")) break;

	            String checkCourse = "SELECT * FROM Courses WHERE course_code=?";
	            PreparedStatement ps3 = con.prepareStatement(checkCourse);
	            ps3.setString(1, code);

	            ResultSet rs3 = ps3.executeQuery();

	            if (!rs3.next()) {
	                System.out.println("Invalid course");
	                continue;
	            }

	            int credits = rs3.getInt("credits");
	            String pre = rs3.getString("prerequisite");
	            String type = rs3.getString("course_type");

	            if (semCredit + credits > 20) {
	                System.out.println("Semester credit limit exceeded");
	                continue;
	            }

	  
	            if (type.equalsIgnoreCase("Elective")) {

	                String coreCheck =
	                    "SELECT * FROM Courses c " +
	                    "WHERE c.semester=? AND c.course_type='Core' " +
	                    "AND c.course_code NOT IN (" +
	                    "SELECT code FROM Student_Course WHERE sid=?)";

	                PreparedStatement psCore = con.prepareStatement(coreCheck);
	                psCore.setInt(1, currentSem);
	                psCore.setString(2, sid);

	                ResultSet rsCore = psCore.executeQuery();

	                if (rsCore.next()) {
	                    System.out.println("You must register all Core courses first");
	                    continue;
	                }
	            }

	            if (pre != null && !pre.equals("")) {

	                String checkPre =
	                    "SELECT * FROM Student_Course WHERE sid=? AND code=? AND grade IS NOT NULL";
	                PreparedStatement ps5 = con.prepareStatement(checkPre);
	                ps5.setString(1, sid);
	                ps5.setString(2, pre);

	                ResultSet rs5 = ps5.executeQuery();

	                if (!rs5.next()) {
	                    System.out.println("Prerequisite not completed");
	                    continue;
	                }
	            }

	            String insert =
	                "INSERT INTO Student_Course (code, title, sid, credits, professor_name, course_regt_date, drop_deadline, grade) " +
	                "VALUES (?, ?, ?, ?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 1 MONTH), NULL)";

	            PreparedStatement ps6 = con.prepareStatement(insert);

	            ps6.setString(1, code);
	            ps6.setString(2, rs3.getString("title"));
	            ps6.setString(3, sid);
	            ps6.setInt(4, credits);
	            ps6.setString(5, "");

	            ps6.executeUpdate();

	            String updateCredit =
	                "UPDATE Students SET sem_credit = sem_credit + ? WHERE sid=?";
	            PreparedStatement ps7 = con.prepareStatement(updateCredit);

	            ps7.setInt(1, credits);
	            ps7.setString(2, sid);
	            ps7.executeUpdate();

	            semCredit += credits;

	            System.out.println("Course Registered Successfully");
	        }

	        con.close();

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}

	public static void dropCourse() {

		String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);

		    try {
		        Connection con = DriverManager.getConnection(url, user, pass);

		        String sid = Student.currentStudentId;

		        System.out.print("Enter Course Code: ");
		        String code = sc.nextLine();

		        String check =
		            "SELECT credits, drop_deadline FROM Student_Course WHERE sid=? AND code=?";
		        PreparedStatement ps1 = con.prepareStatement(check);

		        ps1.setString(1, sid);
		        ps1.setString(2, code);

		        ResultSet rs = ps1.executeQuery();

		        if (!rs.next()) {
		            System.out.println("Course not found");
		            return;
		        }
		        
		        Date deadline = rs.getDate("drop_deadline");
		        Date today = new Date(System.currentTimeMillis());

		        if (today.after(deadline)) {
		            System.out.println("Drop deadline passed");
		            return;
		        }

		        int credits = rs.getInt("credits");

		        String delete = "DELETE FROM Student_Course WHERE sid=? AND code=?";
		        PreparedStatement ps2 = con.prepareStatement(delete);

		        ps2.setString(1, sid);
		        ps2.setString(2, code);
		        ps2.executeUpdate();

		        String updateCredit =
		            "UPDATE Students SET sem_credit = sem_credit - ? WHERE sid=?";
		        PreparedStatement ps3 = con.prepareStatement(updateCredit);

		        ps3.setInt(1, credits);
		        ps3.setString(2, sid);
		        ps3.executeUpdate();

		        System.out.println("Course dropped successfully");

		        con.close();

		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		    }
		
	}

    public static void viewSchedule() {
    	
    	String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);


		    try {
		        Connection con = DriverManager.getConnection(url, user, pass);

		        String query =
		            "SELECT s.day, s.timing, s.location, s.course_code, s.prof_name " +
		            "FROM Schedule s " +
		            "JOIN Student_Course sc ON s.course_code = sc.code " +
		            "WHERE sc.sid=?";

		        PreparedStatement ps = con.prepareStatement(query);
		        ps.setString(1, Student.currentStudentId);

		        ResultSet rs = ps.executeQuery();

		        Map<String, List<String[]>> scheduleMap = new HashMap<>();

		        while (rs.next()) {

		            String day = rs.getString("day");
		            String timing = rs.getString("timing");

		            String entry =
		                rs.getString("course_code") +
		                "[" + timing +
		                " | " + rs.getString("location") +
		                " | " + rs.getString("prof_name") + "]";

		            scheduleMap.computeIfAbsent(day, k -> new ArrayList<>())
		                       .add(new String[]{timing, entry});
		        }

		        if (scheduleMap.isEmpty()) {
		            System.out.println("No schedule found");
		            return;
		        }

		        List<String> days = Arrays.asList(
		            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
		        );

		        System.out.println("\n--- Weekly Schedule ---\n");

		        for (String day : days) {

		            System.out.print(day + "    ");

		            List<String[]> courses = scheduleMap.get(day);

		            if (courses != null) {

		
		                courses.sort((a, b) -> {
		                    int t1 = Integer.parseInt(a[0].split("-")[0]);
		                    int t2 = Integer.parseInt(b[0].split("-")[0]);
		                    return t1 - t2;
		                });

		                for (String[] c : courses) {
		                    System.out.print(c[1] + "    ");
		                }
		            }

		            System.out.println();
		        }

		        con.close();

		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		    }
		}
    

    public static void trackAcademicProgress() {

    	String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);
		
		try {
	        Connection con = DriverManager.getConnection(url, user, pass);

	        String sid = Student.currentStudentId;

	        String semQuery = "SELECT semster, sgpa, cgpa FROM Students WHERE sid=?";
	        PreparedStatement ps0 = con.prepareStatement(semQuery);
	        ps0.setString(1, sid);

	        ResultSet rs0 = ps0.executeQuery();

	        if (!rs0.next()) {
	            System.out.println("Student not found");
	            return;
	        }

	        int currentSem = rs0.getInt("semster");

	        System.out.println("Previous SGPA: " + rs0.getFloat("sgpa"));
	        System.out.println("Previous CGPA: " + rs0.getFloat("cgpa"));

	        String check =
	            "SELECT * FROM Student_Course sc " +
	            "JOIN Courses c ON sc.code = c.course_code " +
	            "WHERE sc.sid=? AND c.semester=? AND sc.grade IS NULL";

	        PreparedStatement ps1 = con.prepareStatement(check);
	        ps1.setString(1, sid);
	        ps1.setInt(2, currentSem);

	        ResultSet rs1 = ps1.executeQuery();

	        if (rs1.next()) {
	            System.out.println("Grades for this semester are not fully assigned yet");
	            return;
	        }

	        String semQueryCourses =
	            "SELECT sc.credits, sc.grade " +
	            "FROM Student_Course sc " +
	            "JOIN Courses c ON sc.code = c.course_code " +
	            "WHERE sc.sid=? AND c.semester=?";

	        PreparedStatement ps2 = con.prepareStatement(semQueryCourses);
	        ps2.setString(1, sid);
	        ps2.setInt(2, currentSem);

	        ResultSet rs2 = ps2.executeQuery();

	        double semPoints = 0;
	        int semCredits = 0;

	        boolean fail = false;

	        while (rs2.next()) {

	            String grade = rs2.getString("grade");
	            int credits = rs2.getInt("credits");

	            int gp = getGradePoint(grade);

	            if (grade.equals("FF")) {
	                fail = true;
	            } else {
	                semCredits += credits;
	            }

	            semPoints += gp * credits;
	        }

	        if (semCredits == 0) {
	            System.out.println("No valid credits for SGPA");
	            return;
	        }

	        double sgpa = semPoints / semCredits;

	        String allQuery = "SELECT credits, grade FROM Student_Course WHERE sid=?";
	        PreparedStatement ps3 = con.prepareStatement(allQuery);
	        ps3.setString(1, sid);

	        ResultSet rs3 = ps3.executeQuery();

	        double totalPoints = 0;
	        int totalCredits = 0;

	        while (rs3.next()) {

	            String grade = rs3.getString("grade");
	            int credits = rs3.getInt("credits");

	            int gp = getGradePoint(grade);

	            if (!grade.equals("FF")) {
	                totalCredits += credits;
	                totalPoints += gp * credits;
	            }
	        }

	        double cgpa = totalPoints / totalCredits;

	        System.out.println("\nSGPA (Current Semester): " + sgpa);
	        System.out.println("CGPA (Overall): " + cgpa);

	        if (fail) {
	            System.out.println("You have failed in one or more subjects (FF)");
	        }

	        String update =
	            "UPDATE Students SET sgpa=?, cgpa=? WHERE sid=?";
	        PreparedStatement ps4 = con.prepareStatement(update);

	        ps4.setDouble(1, sgpa);
	        ps4.setDouble(2, cgpa);
	        ps4.setString(3, sid);

	        ps4.executeUpdate();

	        System.out.println("Academic progress updated successfully");

	        con.close();

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
    }
    
    public static int getGradePoint(String grade) {

        if (grade == null) return 0;

        switch (grade) {
            case "AA": return 10;
            case "AB": return 9;
            case "BB": return 8;
            case "BC": return 7;
            case "CC": return 6;
            case "DD": return 5;
            case "FF": return 0;
            default: return 0;
        }
    }

    public static void viewRegisteredCourses() {
    	
    	String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            String query =
                "SELECT code, title, credits, grade, course_regt_date " +
                "FROM Student_Course WHERE sid=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Student.currentStudentId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            System.out.println("\n--- Registered Courses ---");

            while (rs.next()) {
                found = true;

                System.out.println(
                    rs.getString("code") + " | " +
                    rs.getString("title") + " | Credits: " +
                    rs.getInt("credits") + " | Grade: " +
                    rs.getString("grade") + " | Registered: " +
                    rs.getDate("course_regt_date")
                );
            }

            if (!found) {
                System.out.println("No courses registered");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void submitComplaint() {
    	
    	String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            System.out.print("Enter Complaint: ");
            String desc = sc.nextLine();

            String query = "INSERT INTO Complaint VALUES (NULL, ?, ?, CURDATE(), 'Pending', NULL)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "Student " + Student.currentStudentId);
            ps.setString(2, desc);

            ps.executeUpdate();

            System.out.println("Complaint submitted");

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void viewComplaints() {

    	String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);
		
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            String query = "SELECT * FROM Complaint WHERE name=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "Student " + Student.currentStudentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("description") + " | " +
                    rs.getString("status")
                );
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void checkCreditLoad() {

    	String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);
		
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            String query = "SELECT SUM(credits) FROM Student_Course WHERE sid=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, Student.currentStudentId);

            ResultSet rs = ps.executeQuery();

            rs.next();

            int total = rs.getInt(1);

            System.out.println("Total Registered Credits: " + total);

            if (total > 20) {
                System.out.println("Warning: Credit limit exceeded");
            } else if (total == 20) {
                System.out.println("Maximum credit limit reached");
            } else {
                System.out.println("You can register more courses");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void viewCompletedCourses() {

    	String url = "jdbc:mysql://localhost:3306/"+Professor.dpt;
        String user = "root";
        String pass = "mayank09";
		Scanner sc=new Scanner(System.in);
		
        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            String query =
                "SELECT code, title, credits, grade " +
                "FROM Student_Course WHERE sid=? AND grade IS NOT NULL";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Student.currentStudentId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            System.out.println("\n--- Completed Courses ---");

            while (rs.next()) {
                found = true;

                System.out.println(
                    rs.getString("code") + " | " +
                    rs.getString("title") + " | Credits: " +
                    rs.getInt("credits") + " | Grade: " +
                    rs.getString("grade")
                );
            }

            if (!found) {
                System.out.println("No completed courses yet");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
