package fr.eni.ecole.jdbcTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
	static Connection cnx = null;
	
	static {
		try {
			Class.forName(Settings.getProperty("driverjdbc"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		cnx = DriverManager.getConnection(Settings.getProperty("url"), Settings.getProperty("user"), Settings.getProperty("password"));
		return cnx;
	}
	
	public static void closeConnection() {
		try {
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
