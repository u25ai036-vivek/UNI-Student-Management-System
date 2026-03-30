package frontEnd;

import java.util.Scanner;

public class StudentMenu {
	public static void show() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Profile");
            System.out.println("2. View Available Courses");
            System.out.println("3. Register Course");
            System.out.println("4. View Schedule");
            System.out.println("5. Track Academic Progress");
            System.out.println("6. Drop Courses");
            System.out.println("7. Submit Complaints");            
            System.out.println("8. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // call JDBC method
                    System.out.println("Displaying profile...");
                    break;
                case 2:
                    System.out.println("Displaying Available Courses...");
                    break;
                    
                case 3:System.out.println("Course Registration...");
                    break;
                         
                case 4:System.out.println("Schedule...");
                	break;
              	
                case 5:System.out.println("Academic Progress..");
                	break;
              
                case 6:System.out.println("Dropping courses...");
                	break;
                
                case 7:System.out.println("Submitting Complaint...");
                	break;
                
                case 8:
                    return;
            }
        }
    
}



}
