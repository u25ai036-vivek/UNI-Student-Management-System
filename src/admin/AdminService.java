package admin;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import frontEnd.*;

import java.sql.*;
import java.util.Scanner;

public class AdminService implements AdminOperations 
{

	    

	    public static void createCourse() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	        	
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("Course Code: ");
	            String code = sc.nextLine();

	            System.out.print("Title: ");
	            String title = sc.nextLine();

	            System.out.print("Credits: ");
	            int credits = sc.nextInt();
	            sc.nextLine();

	            System.out.print("Prerequisite: ");
	            String pre = sc.nextLine();

	            System.out.print("Semester: ");
	            String sem = sc.nextLine();

	            System.out.print("Course Type: ");
	            String type = sc.nextLine();

	            System.out.print("Syllabus: ");
	            String syllabus = sc.nextLine();

	            String query = "INSERT INTO Courses VALUES (?,?,?,?,?,?,?)";

	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, code);
	            ps.setString(2, title);
	            ps.setInt(3, credits);
	            ps.setString(4, pre);
	            ps.setString(5, sem);
	            ps.setString(6, type);
	            ps.setString(7, syllabus);

	            ps.executeUpdate();

	            System.out.println("Course added");

	            con.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }

	    public static void removeCourse() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("Enter Course Code: ");
	            String code = sc.nextLine();

	            String query = "DELETE FROM Courses WHERE course_code=?";

	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, code);

	            ps.executeUpdate();

	            System.out.println("Course deleted");

	            con.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
        	MIS.handleAdminMenu();
        }
	    }

	    public static void editCourseDetails() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;

	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("Enter Course Code: ");
	            String code = sc.nextLine();

	            String selectQuery = "SELECT * FROM Courses WHERE course_code=?";
	            PreparedStatement ps1 = con.prepareStatement(selectQuery);
	            ps1.setString(1, code);
	            ResultSet rs = ps1.executeQuery();

	            if (!rs.next()) {
	                System.out.println("Course not found");
	                con.close();
	                return;
	            }

	            int oldCredits = rs.getInt("credits");
	            String oldPre = rs.getString("prerequisite");
	            String oldSem = rs.getString("semester");
	            String oldType = rs.getString("course_type");
	            String oldSyllabus = rs.getString("syllabus");

	            System.out.println("Enter new values (0 to keep same)");

	            System.out.print("Credits: ");
	            int credits = sc.nextInt();
	            sc.nextLine();

	            System.out.print("Prerequisite: ");
	            String pre = sc.nextLine();

	            System.out.print("Semester: ");
	            String sem = sc.nextLine();

	            System.out.print("Course Type: ");
	            String type = sc.nextLine();

	            System.out.print("Syllabus: ");
	            String syllabus = sc.nextLine();

	            if (credits == 0) credits = oldCredits;
	            if (pre.equals("0")) pre = oldPre;
	            if (sem.equals("0")) sem = oldSem;
	            if (type.equals("0")) type = oldType;
	            if (syllabus.equals("0")) syllabus = oldSyllabus;

	            String updateQuery = "UPDATE Courses SET credits=?, prerequisite=?, semester=?, course_type=?, syllabus=? WHERE course_code=?";

	            PreparedStatement ps2 = con.prepareStatement(updateQuery);
	            ps2.setInt(1, credits);
	            ps2.setString(2, pre);
	            ps2.setString(3, sem);
	            ps2.setString(4, type);
	            ps2.setString(5, syllabus);
	            ps2.setString(6, code);

	            ps2.executeUpdate();

	            System.out.println("Course updated successfully");

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }

	    public static void assignProfessorToCourse() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("Enter Course Code: ");
	            String code = sc.nextLine();

	            String selectQuery = "SELECT title, credits FROM Courses WHERE course_code=?";
	            PreparedStatement ps1 = con.prepareStatement(selectQuery);
	            ps1.setString(1, code);
	            ResultSet rs = ps1.executeQuery();

	            if (!rs.next()) {
	                System.out.println("Course not found");
	                con.close();
	                return;
	            }

	            String title = rs.getString("title");
	            int credits = rs.getInt("credits");

	            System.out.print("Enter Class Name (A1/B1): ");
	            String className = sc.nextLine();

	            System.out.print("Enter Student Limit: ");
	            int limit = sc.nextInt();
	            sc.nextLine();

	            String insertQuery = "INSERT INTO Professor_Course VALUES (?,?,?,?,?,?)";

	            PreparedStatement ps2 = con.prepareStatement(insertQuery);
	            ps2.setString(1, code);
	            ps2.setString(2, title);
	            ps2.setInt(3, credits);
	            ps2.setString(4, className);
	            ps2.setInt(5, 0);
	            ps2.setInt(6, limit);

	            ps2.executeUpdate();

	            System.out.println("Course assigned successfully");

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
        	MIS.handleAdminMenu();
        }
	    }

	    public static void retrieveStudentRecords() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            String query = "SELECT * FROM Students";

	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(query);

	            while (rs.next()) {
	                System.out.println(rs.getInt("sid") + " " +
	                        rs.getString("name") + " " +
	                        rs.getFloat("cgpa"));
	            }

	            con.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }

	    public static void editStudentDetails() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("Enter Student ID: ");
	            int sid = sc.nextInt();
	            sc.nextLine();

	            String selectQuery = "SELECT name, contact FROM Students WHERE sid=?";
	            PreparedStatement ps1 = con.prepareStatement(selectQuery);
	            ps1.setInt(1, sid);
	            ResultSet rs = ps1.executeQuery();

	            if (!rs.next()) {
	                System.out.println("Student not found");
	                con.close();
	                return;
	            }

	            String oldName = rs.getString("name");
	            String oldContact = rs.getString("contact");

	            System.out.println("Enter new values (0 to keep same)");

	            System.out.print("Name: ");
	            String name = sc.nextLine();

	            System.out.print("Contact: ");
	            String contact = sc.nextLine();

	            if (name.equals("0")) name = oldName;
	            if (contact.equals("0")) contact = oldContact;

	            String updateQuery = "UPDATE Students SET name=?, contact=? WHERE sid=?";

	            PreparedStatement ps2 = con.prepareStatement(updateQuery);
	            ps2.setString(1, name);
	            ps2.setString(2, contact);
	            ps2.setInt(3, sid);

	            ps2.executeUpdate();

	            System.out.println("Student updated successfully");

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }

	    public static void listAllComplaints() {
	    	
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            String query = "SELECT * FROM Complaint";

	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(query);

	            while (rs.next()) {
	                System.out.println(rs.getInt("id") + " " +
	                        rs.getString("description") + " " +
	                        rs.getString("status"));
	            }

	            con.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }

	    public static void searchComplaintsByStatus() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("Enter Status: ");
	            String status = sc.nextLine();

	            String query = "SELECT * FROM Complaint WHERE status=?";

	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, status);

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                System.out.println(rs.getInt("id") + " " +
	                        rs.getString("description"));
	            }

	            con.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }

	    public static void searchComplaintsByDate() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("Enter Date (YYYY-MM-DD): ");
	            String date = sc.nextLine();

	            String query = "SELECT * FROM Complaint WHERE date=?";

	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, date);

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                System.out.println(rs.getInt("id") + " " +
	                        rs.getString("description")+" "+ rs.getDate("date"));
	            }

	            con.close();
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }

	    public static void closeComplaint() {

	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("Enter Complaint ID: ");
	            int id = sc.nextInt();
	            sc.nextLine();

	            String selectQuery = "SELECT sid,name,description, status FROM Complaint WHERE complaint_id=?";
	            PreparedStatement ps1 = con.prepareStatement(selectQuery);
	            ps1.setInt(1, id);
	            ResultSet rs = ps1.executeQuery();

	            if (!rs.next()) {
	                System.out.println("Complaint not found");
	                con.close();
	                return;
	            }

	            String desc = rs.getString("description");
	            String status = rs.getString("status");
	            String sid=rs.getString("sid");
	            String name=rs.getString("name");

	            System.out.println("Student ID: "+ sid);
	            System.out.println("Student name: "+ name);
	            System.out.println("Complaint Description: " + desc);
	            System.out.println("Current Status: " + status);	

	            if (status.equalsIgnoreCase("Resolved")) {
	                System.out.println("Already resolved");
	                con.close();
	                return;
	            }

	            System.out.print("Enter Resolution: ");
	            String res = sc.nextLine();

	            System.out.print("Confirm resolve? (yes/no): ");
	            String confirm = sc.nextLine();

	            if (!confirm.equalsIgnoreCase("yes")) {
	                System.out.println("Operation cancelled");
	                con.close();
	                return;
	            }

	            String updateQuery = "UPDATE Complaint SET status='Resolved', resolution=? WHERE id=?";
	            PreparedStatement ps2 = con.prepareStatement(updateQuery);
	            ps2.setString(1, res);
	            ps2.setInt(2, id);

	            ps2.executeUpdate();

	            System.out.println("Complaint resolved successfully");

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }
	    
	    public  static void studentList() 
	    {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            String query = "SELECT sid, name, contact, cgpa FROM Students";

	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(query);

	            System.out.println("\n--- Student List ---");

	            while (rs.next()) {
	                System.out.println(
	                    rs.getInt("sid") + " | " +
	                    rs.getString("name") + " | " +
	                    rs.getString("contact") +" | "+ rs.getString("semester")+" | CGPA: " +
	                    rs.getFloat("cgpa")
	                );
	            }

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }
	    
	    public static void profList() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            String query = "SELECT id, name, contact, age, email, expertise FROM Professor";

	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(query);

	            System.out.println("\n--- Professor List ---");

	            while (rs.next()) {
	                System.out.println(
	                    rs.getInt("id") + " | " +
	                    rs.getString("name") + " | " +
	                    rs.getString("contact") + " | " +
	                    rs.getInt("age") + " | " +
	                    rs.getString("email") + " | " +
	                    rs.getString("expertise")
	                );
	            }

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }	
	    }
	    
	    public static void EditProfdetails()
	    {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;

	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("Enter Professor ID: ");
	            int id = sc.nextInt();
	            sc.nextLine();

	            String selectQuery = "SELECT name, contact FROM Professor WHERE id=?";
	            PreparedStatement ps1 = con.prepareStatement(selectQuery);
	            ps1.setInt(1, id);
	            ResultSet rs = ps1.executeQuery();

	            if (!rs.next()) {
	                System.out.println("Professor not found");
	                con.close();
	                return;
	            }

	            String oldName = rs.getString("name");
	            String oldContact = rs.getString("contact");

	            System.out.println("Current Name: " + oldName);
	            System.out.println("Current Contact: " + oldContact);

	            System.out.println("Enter new values (0 to keep same)");

	            System.out.print("Name: ");
	            String name = sc.nextLine();

	            System.out.print("Contact: ");
	            String contact = sc.nextLine();

	            if (name.equals("0")) name = oldName;
	            if (contact.equals("0")) contact = oldContact;

	            String updateQuery = "UPDATE Professor SET name=?, contact=? WHERE id=?";

	            PreparedStatement ps2 = con.prepareStatement(updateQuery);
	            ps2.setString(1, name);
	            ps2.setString(2, contact);
	            ps2.setInt(3, id);

	            ps2.executeUpdate();

	            System.out.println("Professor updated successfully");

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }
	    
	    public static void addStudent() {
	    	Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("SID: ");
	            int sid = sc.nextInt();
	            sc.nextLine();

	            System.out.print("Name: ");
	            String name = sc.nextLine();

	            System.out.print("Contact: ");
	            String contact = sc.nextLine();

	            System.out.print("Email: ");
	            String email = sc.nextLine();

	            System.out.print("Password: ");
	            String password = sc.nextLine();

	            System.out.print("Class Name: ");
	            String className = sc.nextLine();

	            System.out.print("Semester: ");
	            int sem = sc.nextInt();

	            String query = "INSERT INTO Students VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setInt(1, sid);
	            ps.setString(2, name);
	            ps.setString(3, contact);
	            ps.setString(4, email);
	            ps.setString(5, password);
	            ps.setString(6, className);
	            ps.setInt(7, sem);
	            ps.setInt(8, 0);
	            ps.setInt(9, 0);
	            ps.setFloat(10, 0);
	            ps.setFloat(11, 0);

	            ps.executeUpdate();

	            System.out.println("Student added successfully");

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
	        	MIS.handleAdminMenu();
	        }
	    }

		public static void addProffesor() {
			// TOSystem.out.println("Enter department : ");
			Scanner sc = new Scanner(System.in);

		    String user = "root";
		    String pass = "mayank09";
	    	System.out.println("Enter department : ");
	    	String dept=sc.nextLine();
	    	String url = "jdbc:mysql://localhost:3306/"+dept;
	        try {
	            Connection con = DriverManager.getConnection(url, user, pass);

	            System.out.print("ID: ");
	            int id = sc.nextInt();
	            sc.nextLine();

	            System.out.print("Name: ");
	            String name = sc.nextLine();

	            System.out.print("Contact: ");
	            String contact = sc.nextLine();

	            System.out.print("Email: ");
	            String email = sc.nextLine();

	            System.out.print("Password: ");
	            String password = sc.nextLine();

	            System.out.print("Age: ");
	            int age = sc.nextInt();

	            System.out.print("Office Hours Start: ");
	            int start = sc.nextInt();

	            System.out.print("Office Hours End: ");
	            int end = sc.nextInt();
	            sc.nextLine();

	            System.out.print("Expertise: ");
	            String exp = sc.nextLine();

	            String query = "INSERT INTO Professor VALUES (?,?,?,?,?,?,?,?,?)";

	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setInt(1, id);
	            ps.setString(2, name);
	            ps.setString(3, contact);
	            ps.setString(4, email);
	            ps.setString(5, password);
	            ps.setInt(6, age);
	            ps.setInt(7, start);
	            ps.setInt(8, end);
	            ps.setString(9, exp);

	            ps.executeUpdate();

	            System.out.println("Professor added successfully");

	            con.close();

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        finally {
        	MIS.handleAdminMenu();
        }
		}
	}

