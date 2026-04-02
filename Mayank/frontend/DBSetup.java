package frontend;

import java.sql.*;
import java.util.Scanner;

public class DBSetup {

    public static void addDept() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter department name: ");
        String name = sc.nextLine();

        System.out.println("Enter department abbreviation: ");
        String abv = sc.nextLine();

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "mayank09";

        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            stmt = con.createStatement();

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + abv);
            stmt.executeUpdate("USE " + abv);

            String ddlq1 = "CREATE TABLE IF NOT EXISTS Students (" +
                    "sid VARCHAR(20) PRIMARY KEY," +
                    "name VARCHAR(30)," +
                    "contact VARCHAR(15)," +
                    "email VARCHAR(25)," +
                    "password VARCHAR(20)," +
                    "class_name VARCHAR(10)," +
                    "semester INT," +
                    "total_credit INT," +
                    "sem_credit INT," +
                    "sgpa FLOAT," +
                    "cgpa FLOAT" +
                    ")";

            String ddlq2 = "CREATE TABLE IF NOT EXISTS Complaint (" +
                    "complaint_id VARCHAR(20) ," + "sid VARCHAR(20),"+
                    "name VARCHAR(25)," +
                    "description TEXT," +
                    "date DATE," +
                    "status VARCHAR(50)," +
                    "resolution TEXT" +
                    ")";

            String ddlq3 = "CREATE TABLE IF NOT EXISTS Professor (" +
                    "id VARCHAR(20) PRIMARY KEY," +
                    "name VARCHAR(25)," +
                    "contact VARCHAR(15)," +
                    "email VARCHAR(25)," +
                    "password VARCHAR(20)," +
                    "age INT," +
                    "office_hours_start INT," +
                    "office_hours_end INT," +
                    "expertise VARCHAR(100)" +
                    ")";

            String ddlq4 = "CREATE TABLE IF NOT EXISTS Courses (" +
                    "course_code VARCHAR(10) PRIMARY KEY," +
                    "title VARCHAR(20)," +
                    "credits INT," +
                    "prerequisite VARCHAR(100)," +
                    "semester VARCHAR(20)," +
                    "course_type VARCHAR(50)," +
                    "syllabus TEXT" +
                    ")";

            String ddlq5 = "CREATE TABLE IF NOT EXISTS Student_Course (" +
                    "code VARCHAR(20)," +
                    "title VARCHAR(100)," +
                    "sid VARCHAR(20)," +
                    "credits INT," +
                    "professor_name VARCHAR(100)," +
                    "grade VARCHAR(5)," +
                    "course_regt_date DATE," +
                    "drop_deadline DATE)";

            String ddlq6 = "CREATE TABLE IF NOT EXISTS Professor_Course (" +
                    "code VARCHAR(20)," + "prof_id VARCHAR(20),"+
                    "title VARCHAR(100)," +
                    "credits INT," +
                    "class_name VARCHAR(50)," +
                    "students_enrolled INT," +
                    "student_limit INT" +
                    ")";

            String ddlq7 = "CREATE TABLE IF NOT EXISTS Schedule (" +
                    "class_name VARCHAR(50)," +
                    "timing VARCHAR(50)," +
                    "location VARCHAR(100)," +
                    "prof_name VARCHAR(100)," +
                    "day VARCHAR(20)," +
                    "course_code VARCHAR(20)" +
                    ")";

            stmt.executeUpdate(ddlq1);
            stmt.executeUpdate(ddlq2);
            stmt.executeUpdate(ddlq3);
            stmt.executeUpdate(ddlq4);
            stmt.executeUpdate(ddlq5);
            stmt.executeUpdate(ddlq6);
            stmt.executeUpdate(ddlq7);

            System.out.println("Department database created successfully.");

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {}

            try {
                if (con != null) con.close();
            } catch (Exception e) {}
        }
    }
}
