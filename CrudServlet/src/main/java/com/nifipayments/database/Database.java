package com.nifipayments.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	public static Connection conn = null;

	public static Connection getConnection() {

		if (conn != null) {
			return conn;
		} else {
			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servletcrud", "root", "");
//				return conn;
				Class.forName(DBInfo.DRIVER_NAME);
				conn = DriverManager.getConnection(DBInfo.URL, DBInfo.USER_NAME, DBInfo.PASSWORD);
				return conn;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
	}

	public static void main(String[] args) {
		System.out.println(getConnection());
	}

}
