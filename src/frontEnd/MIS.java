package frontEnd;
import java.util.*;

import admin.AdminOperations;
import admin.AdminService;
import admin.Admin;
import proffesor.ProfOperations;
import proffesor.ProfService;
import proffesor.Professor;
import student.StudentService;
import student.Student;

public class MIS {	

    static StudentService studentService = new StudentService();
    static ProfService professorService = new ProfService();
    static AdminService adminService = new AdminService();

    public static void main(String[] args) 
    {
    	Scanner sc=new Scanner(System.in);
    	
        while (true) {
            displayMainMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    exitApplication();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    

    static void displayMainMenu() {
        System.out.println("\n===== MIS SYSTEM =====");
        System.out.println("1. Enter Application");
        System.out.println("2. Exit");
        System.out.print("Enter choice: ");
    }

    static void handleLogin() {
    	
    	Scanner sc=new Scanner(System.in);

        System.out.println("\nLogin as:");
        System.out.println("1. Student");
        System.out.println("2. Professor");
        System.out.println("3. Admin");

        int role = sc.nextInt();
        sc.nextLine();

        

        switch (role) {
            case 1:
            	System.out.println("On first login ,password is contact no.");
            	System.out.print("Enter emial id: ");
                String email = sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();
                
                Student student = new Student();
                if (student.login(email, password)) {
                    handleStudentMenu();
                } else {
                    System.out.println("Login failed");
                }
                break;

            case 2:
            	
            	System.out.print("Enter email id: ");
                String email1 = sc.nextLine();

                System.out.print("Enter Password: ");
                String password1 = sc.nextLine();
                
                Professor professor = new Professor();
                if (professor.login(email1, password1)) {
                    handleProfessorMenu();
                } else {
                    System.out.println("Login failed");
                }
                break;

            case 3:
                Admin admin = new Admin();
                
                System.out.print("Enter UserName: ");
                String id = sc.nextLine();

                System.out.print("Enter Password: ");
                String password11 = sc.nextLine();
                
                if (admin.login(id, password11)) {
                    handleAdminMenu();
                } else {
                    System.out.println("Login failed");
                }
                break;

            default:
                System.out.println("Invalid role");
        }
    }


    public static void handleStudentMenu() {

        StudentService s = new StudentService();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. Course Operations");
            System.out.println("2. Academic Details");
            System.out.println("3. Schedule");
            System.out.println("4. Complaints");
            System.out.println("0. Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

             
                case 1:
                    while (true) {

                        System.out.println("\n--- Course Operations ---");
                        System.out.println("1. View Available Courses");
                        System.out.println("2. Register Course");
                        System.out.println("3. Drop Course");
                        System.out.println("4. View Registered Courses");
                        System.out.println("0. Back");

                        int c = sc.nextInt();
                        sc.nextLine();

                        switch (c) {
                            case 1: s.viewAvailableCourses(); break;
                            case 2: s.registerCourse(); break;
                            case 3: s.dropCourse(); break;
                            case 4: s.viewRegisteredCourses(); break;
                            case 0: break;
                            default: System.out.println("Invalid choice");
                        }

                        if (c == 0) break;
                    }
                    break;

             
                case 2:
                    while (true) {

                        System.out.println("\n--- Academic Details ---");
                        System.out.println("1. Track Academic Progress");
                        System.out.println("2. Check Credit Load");
                        System.out.println("3. View Completed Courses");
                        System.out.println("0. Back");

                        int c = sc.nextInt();
                        sc.nextLine();

                        switch (c) {
                            case 1: s.trackAcademicProgress(); break;
                            case 2: s.checkCreditLoad(); break;
                            case 3: s.viewCompletedCourses(); break;
                            case 0: break;
                            default: System.out.println("Invalid choice");
                        }

                        if (c == 0) break;
                    }
                    break;

                case 3:
                    s.viewSchedule();
                    break;

                
                case 4:
                    while (true) {

                        System.out.println("\n--- Complaint Section ---");
                        System.out.println("1. Submit Complaint");
                        System.out.println("2. View Complaints");
                        System.out.println("0. Back");

                        int c = sc.nextInt();
                        sc.nextLine();

                        switch (c) {
                            case 1: s.submitComplaint(); break;
                            case 2: s.viewComplaints(); break;
                            case 0: break;
                            default: System.out.println("Invalid choice");
                        }

                        if (c == 0) break;
                    }
                    break;

                case 0:
                    System.out.println("Logged out successfully");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }


        
    public static void handleProfessorMenu() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== PROFESSOR MENU =====");
            System.out.println("1. View Courses");
            System.out.println("2. Course Management");
            System.out.println("3. Student Management");
            System.out.println("4. Grading");
            System.out.println("5. View Schedule");
            System.out.println("0. Logout");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    ProfOperations.listAssignedCourses();
                    break;

                case 2:
                    while (true) {

                        System.out.println("\n--- COURSE MANAGEMENT ---");
                        System.out.println("1. Change Credits");
                        System.out.println("2. Change Syllabus");
                        System.out.println("3. Change Prerequisites");
                        System.out.println("4. Change Timings");
                        System.out.println("5. Change Enrollment Limit");
                        System.out.println("0. Back");

                        int c = sc.nextInt();
                        sc.nextLine();

                        switch (c) {

                            case 1:
                            	ProfOperations.changeCredits();
                                break;

                            case 2:
                            	ProfOperations.changeSyllabus();
                                break;

                            case 3:
                            	ProfOperations.changePrerequisites();
                                break;

                            case 4:
                            	ProfOperations.changeTimings();
                                break;

                            case 5:
                            	ProfOperations.changeEnrollmentLimit();
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Invalid choice");
                        }

                        if (c == 0) break;
                    }
                    break;

                case 3:
                              	ProfOperations.listEnrolledStudents();
                                break;



                case 4:
                	ProfOperations.assignGrade();
                    break;
                    
                case 5:
                	ProfOperations.viewSchedule();
                	break;

                case 0:
                    System.out.println("Logged out successfully");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
	
	
	
	public static void handleAdminMenu() {
		
		Scanner sc=new Scanner(System.in);

	    while (true) {

	        System.out.println("\n===== Admin Menu =====");
	        System.out.println("1. Student Management");
	        System.out.println("2. Professor Management");
	        System.out.println("3. Course Management");
	        System.out.println("4. Complaint Management");
	        System.out.println("5. Department Setup");
	        System.out.println("6. Go Back");

	        int ch = sc.nextInt();
	        sc.nextLine();

	        switch (ch) {

	            case 1:
	                while (true) {
	                    System.out.println("\n--- Student Management ---");
	                    System.out.println("1. View Students");
	                    System.out.println("2. Add Student");
	                    System.out.println("3. Edit Student");
	                    System.out.println("4. Retrieve Records");
	                    System.out.println("5. Go Back");

	                    int s = sc.nextInt();
	                    sc.nextLine();

	                    switch (s) {
	                        case 1:
	                            AdminOperations.studentList();
	                            break;
	                        case 2:
	                            AdminOperations.addStudent();
	                            break;
	                        case 3:
	                            AdminOperations.editStudentDetails();
	                            break;
	                        case 4:
	                            AdminOperations.retrieveStudentRecords();
	                            break;
	                        case 5:
	                        	DBSetup.addDept();
	                        	break;
	                        default:
	                            System.out.println("Invalid choice");
	                    }

	                    if (s == 6) break;
	                }
	                break;

	            case 2:
	                while (true) {
	                    System.out.println("\n--- Professor Management ---");
	                    System.out.println("1. View Professors");
	                    System.out.println("2. Add Professor");
	                    System.out.println("3. Edit Professor");
	                    System.out.println("4. Go Back");

	                    int p = sc.nextInt();
	                    sc.nextLine();

	                    switch (p) {
	                        case 1:
	                            AdminOperations.profList();
	                            break;
	                        case 2:
	                            AdminOperations.addProffesor();
	                            break;
	                        case 3:
	                            AdminOperations.EditProfdetails();
	                            break;
	                        case 4:
	                            break;
	                        default:
	                            System.out.println("Invalid choice");
	                    }

	                    if (p == 4) break;
	                }
	                break;

	            case 3:
	                while (true) {
	                    System.out.println("\n--- Course Management ---");
	                    System.out.println("1. Add Course");
	                    System.out.println("2. Remove Course");
	                    System.out.println("3. Edit Course");
	                    System.out.println("4. Assign Professor");
	                    System.out.println("5. Go Back");

	                    int c = sc.nextInt();
	                    sc.nextLine();

	                    switch (c) {
	                        case 1:
	                            AdminOperations.createCourse();
	                            break;
	                        case 2:
	                            AdminOperations.removeCourse();
	                            break;
	                        case 3:
	                            AdminOperations.editCourseDetails();
	                            break;
	                        case 4:
	                            AdminOperations.assignProfessorToCourse();
	                            break;
	                        case 5:
	                            break;
	                        default:
	                            System.out.println("Invalid choice");
	                    }

	                    if (c == 5) break;
	                }
	                break;

	            case 4:
	                while (true) {
	                    System.out.println("\n--- Complaint Management ---");
	                    System.out.println("1. View All Complaints");
	                    System.out.println("2. Search by Status");
	                    System.out.println("3. Search by Date");
	                    System.out.println("4. Close Complaint");
	                    System.out.println("5. Go Back");

	                    int cm = sc.nextInt();
	                    sc.nextLine();

	                    switch (cm) {
	                        case 1:
	                            AdminOperations.listAllComplaints();
	                            break;
	                        case 2:
	                            AdminOperations.searchComplaintsByStatus();
	                            break;
	                        case 3:
	                            AdminOperations.searchComplaintsByDate();
	                            break;
	                        case 4:
	                            AdminOperations.closeComplaint();
	                            break;
	                        case 5:
	                            break;
	                        default:
	                            System.out.println("Invalid choice");
	                    }

	                    if (cm == 5) break;
	                }
	                break;

	            case 5:
	                return;

	            default:
	                System.out.println("Invalid choice");
	        }
	    }
	}

    static void exitApplication() 
    {
        System.out.println("Exiting system...");
    }
}
