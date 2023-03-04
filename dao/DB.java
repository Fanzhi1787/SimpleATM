package dao;

import java.sql.*;

public class DB {
	static String driver = "com.mysql.jdbc.Driver"; // mysql驱动类标识字符串
	static String URL = "jdbc:mysql://localhost:3306/ATM?serverTimezone=UTC";
	static String user = "root";
	static String password = "kyle1787";

	// 获取数据库连接，如果出现异常，返回null
	public static Connection getConnection() {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, user, password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = DB.getConnection();
		if (conn != null) {
			System.out.println("连接成功");
		}
		DB.closeConnection(conn);
	}

}
