package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.U_Beans;

public class SearchU_Dao {

	public List<U_Beans> searchByLoginId(String login_id) { //完全一致
		Connection conn = null;
		ArrayList<U_Beans> userList1 = new ArrayList<U_Beans>();

		try {
			conn = DB_Manager.getConnection();//データベースに繋ぐ

			String sql = "SELECT * FROM user WHERE login_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);//ステートメント
			pStmt.setString(1, login_id);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");//loginIdとは、login_idカラムの値を文字列として取り出したものですの意味
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				U_Beans result1 = new U_Beans(id, loginId, name, birthDate, password, createDate,updateDate);
				userList1.add(result1);//userの内容をaddしてないと動かない。
			}
			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				if(conn!=null) {
					try {
						conn.close();
					}catch(SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		return userList1;
	}
	public List<U_Beans> searchByName(String Name) {//部分一致

		Connection conn = null;
		ArrayList<U_Beans> userList2 = new ArrayList<U_Beans>();

		try {
			conn = DB_Manager.getConnection();//データベースに繋ぐ

			String sql = "SELECT * FROM user WHERE name LIKE '% ? %' = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);//ステートメント
			pStmt.setString(1, Name);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");//loginIdとは、login_idカラムの値を文字列として取り出したものですの意味
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				U_Beans result2 = new U_Beans(id, loginId, name, birthDate, password, createDate,updateDate);
				userList2.add(result2);//userの内容をaddしてないと動かない。
			}

			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				if(conn!=null) {
					try {
						conn.close();
					}catch(SQLException e) {
						e.printStackTrace();
						return null;
					}
				}

			}
		return userList2;
	}

	public List<U_Beans> searchByBirth (String birth1,String birth2) {//範囲内全部

		Connection conn = null;
		ArrayList<U_Beans> userList3 = new ArrayList<U_Beans>();

		try {
			conn = DB_Manager.getConnection();//データベースに繋ぐ

			String sql = "SELECT * FROM user WHERE birth_date < '?' AND birth_date > '?'";

			PreparedStatement pStmt = conn.prepareStatement(sql);//ステートメント
			pStmt.setString(1, birth1);
			pStmt.setString(2, birth2);
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");//loginIdとは、login_idカラムの値を文字列として取り出したものですの意味
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				U_Beans result3 = new U_Beans(id, loginId, name, birthDate, password, createDate,updateDate);
				userList3.add(result3);//userの内容をaddしてないと動かない。
			}

			} catch(SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				if(conn!=null) {
					try {
						conn.close();
					}catch(SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		return userList3;
	}
}
