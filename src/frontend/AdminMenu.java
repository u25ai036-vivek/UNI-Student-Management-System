package frontend;
import java.util.Scanner;

public class AdminMenu {
	
	public static void main(String args[]) 
	{
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Manage Courses");
            System.out.println("2. Manage Student Record");
            System.out.println("3. Assign Proffessors to courses:");
            System.out.println("4. Handle complaints");
            System.out.println("5. Back");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    
                    System.out.println("1.View Courses");
                    System.out.println("2.Add Courses");
                    System.out.println("3.Delete Courses");
                    
                    int edit_choice=sc.nextInt();
                    
                    switch(edit_choice)
                    {
                    case 1://call coursesDAO
                    	break;
                    case 2:
                    	System.out.println("Enter Department Name:");
                    	String dep=sc.nextLine();
                    	System.out.println("Enter Course ID:");
                    	String cid=sc.nextLine();
                    	System.out.println("Enter Course Name:");
                    	String c_name=sc.nextLine();
                    	
                    	break;
                    case 3:
                    	break;
                    
                    }
                    
                    break;
                case 2:
                    // call delete
                    break;
                case 3:
                    // call viewStudents()
                    break;
                case 4:
                    break;
                    
                case 5:
                	return;
            }
        }
    }
	
	
}
