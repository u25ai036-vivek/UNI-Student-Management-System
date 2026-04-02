package backend;

import java.sql.*;

public class Student {

    private String sid;
    private String name;
    private int age;
    private String contact;
    private String email;
    private String password;
    private String className;
    private int semester;
    private int totalCredit;
    private int semCredit;
    private float sgpa;
    private float cgpa;

    // Constructor
    public Student(String sid, String name, int age, String contact,
                   String email, String password, String className,
                   int semester, int totalCredit, int semCredit,
                   float sgpa, float cgpa) {

        this.sid = sid;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.className = className;
        this.semester = semester;
        this.totalCredit = totalCredit;
        this.semCredit = semCredit;
        this.sgpa = sgpa;
        this.cgpa = cgpa;
    }

    //Create Student
    public static void createStudent(String sid, String name, int age,
                                     String contact, String email,
                                     String password, String className,
                                     int semester, int totalCredit,
                                     int semCredit, float sgpa, float cgpa) {

        Student s = new Student(sid, name, age, contact, email, password,
                                className, semester, totalCredit,
                                semCredit, sgpa, cgpa);

        exportToDatabase(s);
    }

    //  Insert into DB
    public static void exportToDatabase(Student s) {

        String url = "jdbc:mysql://localhost:3306/ai";

        String query = "INSERT INTO students (sid, name, age, contact, email, password, class_name, semester, total_credit, sem_credit, sgpa, cgpa) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, s.sid);
            pstmt.setString(2, s.name);
            pstmt.setInt(3, s.age);
            pstmt.setString(4, s.contact);
            pstmt.setString(5, s.email);
            pstmt.setString(6, s.password);
            pstmt.setString(7, s.className);
            pstmt.setInt(8, s.semester);
            pstmt.setInt(9, s.totalCredit);
            pstmt.setInt(10, s.semCredit);
            pstmt.setFloat(11, s.sgpa);
            pstmt.setFloat(12, s.cgpa);

            pstmt.executeUpdate();
            System.out.println("Data Saved Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 FETCH from DB
    public static Student importFromDatabase(String sid) {

        String url = "jdbc:mysql://localhost:3306/ai";
        String query = "SELECT * FROM students WHERE sid = ?";

        Student s = null;

        try (Connection con = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, sid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                s = new Student(
                        rs.getString("sid"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("contact"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("class_name"),
                        rs.getInt("semester"),
                        rs.getInt("total_credit"),
                        rs.getInt("sem_credit"),
                        rs.getFloat("sgpa"),
                        rs.getFloat("cgpa")
                );

                System.out.println(" Data Retrieved for SID: " + sid);
            } else {
                System.out.println("❌ No student found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

    //  Display
    public void display() {
        System.out.println("----- Student Details -----");
        System.out.println("SID: " + sid);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Contact: " + contact);
        System.out.println("Email: " + email);
        System.out.println("Class: " + className);
        System.out.println("Semester: " + semester);
        System.out.println("Total Credits: " + totalCredit);
        System.out.println("Semester Credits: " + semCredit);
        System.out.println("SGPA: " + sgpa);
        System.out.println("CGPA: " + cgpa);
    }

    //  Test
    public static void main(String[] args) {

        createStudent("u25ai058", "Vivek", 20,
                "9999999999", "vivek@mail.com", "pass123", "AI",
                3, 60, 20, 8.5f, 8.2f);

        Student s = importFromDatabase("u25ai058");

        if (s != null) 
        {
            s.display();
        }
    }
}