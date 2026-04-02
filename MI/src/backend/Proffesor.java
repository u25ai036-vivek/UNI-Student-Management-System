package backend;

import java.sql.*;


/*

mysql> describe professor;
+--------------------+--------------+------+-----+---------+-------+
| Field              | Type         | Null | Key | Default | Extra |
+--------------------+--------------+------+-----+---------+-------+
| id                 | varchar(8)   | NO   | PRI | NULL    |       |
| name               | varchar(25)  | YES  |     | NULL    |       |
| contact            | varchar(15)  | YES  |     | NULL    |       |
| email              | varchar(25)  | YES  |     | NULL    |       |
| password           | varchar(20)  | YES  |     | NULL    |       |
| age                | int          | YES  |     | NULL    |       |
| office_hours_start | int          | YES  |     | NULL    |       |
| office_hours_end   | int          | YES  |     | NULL    |       |
| expertise          | varchar(100) | YES  |     | NULL    |       |
+--------------------+--------------+------+-----+---------+-------+

same as student

*/

public class Proffesor {

	private String id;
	private String name;
	private String contact;
	private String email;
	private String password;
	private int age;
	private int officeHoursStart;
	private int officeHoursEnd;
	private String expertise;

	//constructor
	public Proffesor(String id, String name, String contact, String email,
					  String password, int age, int officeHoursStart,
					  int officeHoursEnd, String expertise) {

		this.id = id;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.password = password;
		this.age = age;
		this.officeHoursStart = officeHoursStart;
		this.officeHoursEnd = officeHoursEnd;
		this.expertise = expertise;
	}

	//Create Proffesor
	public static void createProffesor(String id, String name, String contact,
									   String email, String password, int age,
									   int officeHoursStart, int officeHoursEnd,
									   String expertise) {

		Proffesor p = new Proffesor(id, name, contact, email, password,
									age, officeHoursStart, officeHoursEnd,
									expertise);

		exportToDatabase(p);
	}

	public static void exportToDatabase(Proffesor p) {

		String url = "jdbc:mysql://localhost:3306/ai";

		String query = "INSERT INTO professor (id, name, contact, email, password, age, office_hours_start, office_hours_end, expertise) "
					 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(url);
			 PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, p.id);
			stmt.setString(2, p.name);
			stmt.setString(3, p.contact);
			stmt.setString(4, p.email);
			stmt.setString(5, p.password);
			stmt.setInt(6, p.age);
			stmt.setInt(7, p.officeHoursStart);
			stmt.setInt(8, p.officeHoursEnd);
			stmt.setString(9, p.expertise);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void importFromDatabase(String id) {

		String url = "jdbc:mysql://localhost:3306/ai";

		String query = "SELECT * FROM professor WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(url);
			 PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("name");
				String contact = rs.getString("contact");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int age = rs.getInt("age");
				int officeHoursStart = rs.getInt("office_hours_start");
				int officeHoursEnd = rs.getInt("office_hours_end");
				String expertise = rs.getString("expertise");

				Proffesor p = new Proffesor(id, name, contact, email, password,
											age, officeHoursStart, officeHoursEnd,
											expertise);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//testing 
	public static void main(String[] args) {
		createProffesor("P001", "Dr. Smith", "1234567890", "dr.smith@university.edu", "password123", 45, 9, 17, "Computer Science");
		importFromDatabase("P001");
	}
}
