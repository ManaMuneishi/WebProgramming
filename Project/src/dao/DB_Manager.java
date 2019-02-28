package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class DB_Manager {//SQLの接続について書いてある。接続のこと以外は書いてない。
		private static String URL = "jdbc:mysql://localhost/";
		private static String DBNAME = "user"; //urlと分けると変更しやすい。
		private static String USER = "root";
		private static String PASS = "password";

		public static Connection getConnection() {//SQLと繋げる
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(URL+DBNAME, USER, PASS);//ここ以外は一緒。
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return con;
		}
	}
