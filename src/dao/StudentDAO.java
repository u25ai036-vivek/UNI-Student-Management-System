package dao;

import java.sql.*;
import src1.backend.Student;

public class StudentDAO {

    public static Student getStudentProfile(String sid) {

        String url = "jdbc:mysql://localhost:3306/ai";
        String query = "SELECT * FROM students WHERE sid = ?";

        Student s = null;

        try (Connection con = DriverManager.getConnection(url, "root", "root");
             PreparedStatement ps = con.prepareStatement(query)) {

            //  Set SID
            ps.setString(1, sid);

            ResultSet rs = ps.executeQuery();

            //  If student exists
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

            } else {
                System.out.println(" No student found with this ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }
}