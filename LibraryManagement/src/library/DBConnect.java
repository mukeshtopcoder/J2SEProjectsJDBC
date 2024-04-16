package library;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static final String url="jdbc:mysql://127.0.0.1:3306/library";
	private static final String user="root";
	private static final String password ="root123";
	public static Connection getConnection()
	{
		try {
			Connection conn = DriverManager.getConnection(url,user,password);
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}