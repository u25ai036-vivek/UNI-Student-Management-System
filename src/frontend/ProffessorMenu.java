package frontend;

import java.util.Scanner;

public class ProffessorMenu {
	
	    public static void main(String args[]) {
	        Scanner sc = new Scanner(System.in);

	        System.out.println("\n--- Select Department ---");
	        System.out.println("1. Computer Engineering");
	        System.out.println("2. Artificial Intelligence Engineering");
	        System.out.println("3. Electrical Engineering");

	        int deptChoice = sc.nextInt();

	        String department = "";

	        switch (deptChoice) {
	            case 1: department = "CE"; break;
	            case 2: department = "AI"; break;
	            case 3: department = "EE"; break;
	            default:
	                System.out.println("Invalid choice");
	                return;
	        }

	        showProfessorOptions(department);
	    }
	    
	    
	    public static void showProfessorOptions(String department) {
	        Scanner sc = new Scanner(System.in);

	        while (true) {
	            System.out.println("\n--- Professor Menu (" + department + ") ---");
	            System.out.println("1. View Courses");
	            System.out.println("2. Update Course Details");
	            System.out.println("3. View Enrolled Students");
	            System.out.println("4. Back");

	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1:
	                    viewCourses(department);
	                    break;
	                case 2:
	                    updateCourse(department);
	                    break;
	                case 3:
	                    viewStudents(department);
	                    break;
	                case 4:
	                    return;
	            }
	        }
	    }
	    public static void viewCourses(String department) {
	        System.out.println("\nCourses in " + department + ":");

	        // Call JDBC or collection method
	        // courseDAO.getCoursesByDepartment(department);

	        System.out.println("Course1 | Credits | Timing");
	        System.out.println("Course2 | Credits | Timing");
	    }
	    public static void updateCourse(String department) {
	        Scanner sc = new Scanner(System.in);
	        
	        System.out.print("Enter New Syllabus: ");
	        String syllabus = sc.nextLine();

	        System.out.print("Enter Class Timing: ");
	        String timing = sc.nextLine();

	        System.out.print("Enter Credits: ");
	        int credits = sc.nextInt();

	        System.out.print("Enter Enrollment Limit: ");
	        int limit = sc.nextInt();

	        // Call JDBC
	        // courseDAO.updateCourse(syllabus, timing, credits, limit);

	        System.out.println("Course updated successfully!");
	    }
	    
	    public static void viewStudents(String department) {
	        Scanner sc = new Scanner(System.in);

	        System.out.print("Enter Course ID: ");
	        int courseId = sc.nextInt();

	        System.out.println("\nStudents Enrolled:");

	        // Call JDBC
	        // enrollmentDAO.getStudentsByCourse(courseId);

	        System.out.println("ID | Name | CGPA | Email");
	            }
	}




