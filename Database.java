// package tec181;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
	
	static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/miniproject";
	static final String USER = "root";
	static final String PWD = "";

	Connection conn;
	PreparedStatement pst;
	
	public static Connection dbConnect() {
		try {
			Class.forName(DB_Driver);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PWD);
			return conn;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
}
