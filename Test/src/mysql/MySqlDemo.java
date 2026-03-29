package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;





public class MySqlDemo 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		String Driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/test";
		String userName ="root";
		String pass="root";
		Connection con=null;//import java.sql.Connection;
		Statement stmt=null;
		String ddlQuary="insert into testforproject (Name) values ('amit')";
		try
		{
			Class.forName(Driver);//Load driver class
			con=DriverManager.getConnection(url,userName,pass);
			stmt=con.createStatement();
			System.out.println();
			// stmt.executeQuery(ddlQuary);//select
			//stmt.executeUpdate(ddlQuary);//update
			stmt.execute(ddlQuary);//both
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
				{
					stmt.close();
					stmt=null;
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		

	}

}
