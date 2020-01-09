package com.bank.util;
import oracle.jdbc.OracleDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "java";
		String password = "sepu";
		Connection connection = DriverManager.getConnection(url, userName, password);
		return connection;
	}
}
