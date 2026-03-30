package Backend;

import java.util.*;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Student 
{
    HashMap<String, String> allData;

    // Constructor (Same as yours)
    public Student(String sid, String name, String age, String contect, String email, String password, String branch)
    {
        allData = new HashMap<>();
        allData.put("sid", sid);
        allData.put("name", name);
        allData.put("age", age); // Note: NOw age is added;
        allData.put("contact", contect);
        allData.put("email", email);
        allData.put("password", password);
        allData.put("class_name", branch);
        allData.put("semester", "1");
        allData.put("total_credit", "0"); // "NULL" string ki jagah "0" use karna better hai
        allData.put("sem_credit", "0");
        allData.put("sgpa", "0.0");
        allData.put("cgpa", "0.0");

    }

    public static void createStudent(String sid, String name, String age, String contect, String email, String password, String branch)
    {
        Student s = new Student(sid, name, age, contect, email, password, branch);
        try {
            ExportToDatabase(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // This method can be used to perform any additional initialization if needed
    }

    public static void ExportToDatabase(Student s) throws Exception 
    {

        //use Enum
        String dbName = s.allData.get("sid").substring(3,4);

        String url = "jdbc:mysql://localhost:3306/"+"ai";
        Connection con = null;
        Statement stmt = null;
        
        

        try
        {
            con = DriverManager.getConnection(url, "root", "root");
        }
        catch(SQLException e)
        {
            System.out.println("Connection Failed!,Suggesion: check branch database is there or not");
        }
        try
        {
        	// ❌ Wrong — fragile, breaks if column order differs
        	String sql ;

        	// ✅ Fixed — explicitly name columns
        	sql = "INSERT INTO students (sid, name, age, contact, email, password, class_name, semester, total_credit, sem_credit, sgpa, cgpa) VALUES "
        	           + "('"+s.allData.get("sid")+"', '"+s.allData.get("name")+"', '"+s.allData.get("age")+"', '"+s.allData.get("contact")+"', '"
        	           + s.allData.get("email")+"', '"+s.allData.get("password")+"', '"+s.allData.get("class_name")+"', "
        	           + s.allData.get("semester")+", "+s.allData.get("total_credit")+", "+s.allData.get("sem_credit")+", "
        	           + s.allData.get("sgpa")+", "+s.allData.get("cgpa")+")";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Data Saved!");

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(ClassCastException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(stmt != null)
                {
                    stmt.close();
                    stmt = null;
                }
                if(con != null)
                {
                    con.close();
                    con = null;
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    
    }
    public static Student importFromDatabase(String sid) throws Exception
    {
        String dbName = sid.substring(3,4);
        String url = "jdbc:mysql://localhost:3306/"+"ai";
        Connection con = null;
        Statement stmt = null;
        Student s = null;
        
        ResultSet rs = null;

        try
        {
            con = DriverManager.getConnection(url, "root", "root");
            String sql = "SELECT * FROM students WHERE sid='"+sid+"'";
            stmt = con.createStatement();
            rs= stmt.executeQuery(sql);
            if(rs.next())    // Create a Student object from the retrieved data
            {
                s = new Student(
                    rs.getString("sid"),
                    rs.getString("name"),
                    rs.getString("age"), // Note: 'age' 
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("class_name")
                );
                
                // Now you can use the Student object 's' as needed
                System.out.println("Data Retrieved for SID: " + sid);
            }
            else
            {
                System.out.println("No student found with SID: " + sid);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(rs != null)
                {
                    rs.close();
                    rs = null;
                }
                if(stmt != null)
                {
                    stmt.close();
                    stmt = null;
                }
                if(con != null)
                {
                    con.close();
                    con = null;
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        return s;
    }


    //checking

    public static void main(String[] args) 
    {
                
        // Example usage:
        createStudent("u25ai056", "John Doe", "20", "1234567890", "john.doe@example.com", "password123", "Class A");
        try {
            Student retrievedStudent = importFromDatabase("u25ai056");
            if (retrievedStudent != null) {
                System.out.println("Retrieved Student Name: " + retrievedStudent.allData.get("name"));
                // You can access other fields similarly
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}