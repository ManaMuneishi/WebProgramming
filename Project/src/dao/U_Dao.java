package dao;

//暗号にするやつのimport。nioでcharset出てくる。前に書いてたやつ勝手に全部消えたけどエラーにならないよくわからない
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.U_Beans;



public class U_Dao { //SQLについて書いてある

	public U_Beans findByLoginId(String loginId, String password) {//ログインするときの。
		Connection conn = null;
		try {
			conn = DB_Manager.getConnection();//データベースに繋ぐ

			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);//ステートメント
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) { //ログインIDの後ろは一個しかない。
				return null;
			}

			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			return new U_Beans(loginIdData, nameData);

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
	}



	public List<U_Beans> findAll(){
		Connection conn = null;
		ArrayList<U_Beans> userList = new ArrayList<U_Beans>();

		try {
			conn = DB_Manager.getConnection();

			String sql = "SELECT * FROM user WHERE id NOT IN(1)";//idが1の人は除外して表示。

			Statement stmt = conn.createStatement();//いるやつ。
			ResultSet rs = stmt.executeQuery(sql);//いるやつ。

			while(rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");//loginIdとは、login_idカラムの値を文字列として取り出したものですの意味
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				U_Beans user = new U_Beans(id, loginId, name, birthDate, password, createDate,updateDate);
				userList.add(user);//userの内容をaddしてないと動かない。
			}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				if (conn !=null) {
					try {
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		return userList;
	}

	public List<U_Beans> findSearch(String loginIdP, String nameP, String birth_dateP1, String birth_dateP2){
		Connection conn = null;
		ArrayList<U_Beans> userList = new ArrayList<U_Beans>();

		try {
			conn = DB_Manager.getConnection();

			String sql = "SELECT * FROM user WHERE id NOT IN(1)";//idが1の人は除外して表示。

			//各コマをそれぞれifで定義 = 一つ一つが独立した定義 = 2つ以上が同時に起こりうる。ので4つの場合の定義だけ書けばいい。

			//loginIdPではないものが(少なくとも一つ以上)nullのとき = loginPが入力されている時 =(loginのみアリとは限らない）以下4つ同じ
			if(!loginIdP.equals("")) {
				sql += " AND login_id = '" + loginIdP + "'"; //preではないのでシングルクオーテーションいる."〜"内を上記sqlにくっつける、の式。
			}

			if(!nameP.equals("")) {
				sql += " AND name LIKE '%" + nameP + "%'";  //『and= ' 』とnamePと『 ' 』。部分検索は『%文字%』の前後に' いる。
			}

			if(!birth_dateP1.equals("")) {
				sql += " AND birth_date > '" + birth_dateP1 + "'";
			}

			if(!birth_dateP2.equals("")) {
				sql += " AND birthDate < '" + birth_dateP2 + "'"; //諸々スペース開ける(ANDの前、＜の後ろ）
			}
			System.out.println(sql);

			Statement stmt = conn.createStatement();//いるやつ。
			ResultSet rs = stmt.executeQuery(sql);//いるやつ。

			while(rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");//loginIdとは、login_idカラムの値を文字列として取り出したものですの意味
				String name = rs.getString("name");
				Date birthDate = rs.getDate("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				U_Beans user = new U_Beans(id, loginId, name, birthDate, password, createDate,updateDate);
				userList.add(user);//userの内容をaddしてないと動かない。
			}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				if (conn !=null) {
					try {
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		return userList; //ここ大事！！findAllの戻り値と同じ名前にする。
	}


	public void insertInfo(String login_id, String password,String name, String birth_date,String create_date,String update_date){//ここ消さないと、ログインできない。

		Connection conn = null;

     	 try {
     		conn = DB_Manager.getConnection();//データベースに繋ぐ

    		String sql = "INSERT INTO user(login_id, password, name, birth_date, create_date, update_date) VALUES (?, ?, ?, ?, now(), now())"; //now()で今の時間が簡単に取得できる。

			PreparedStatement pStmt = conn.prepareStatement(sql);//ステートメント

			String source = password;
			Charset charset = StandardCharsets.UTF_8;//日本語にするやつ
			String algorithm = "MD5";//ハッシュアルゴリズム
			byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));//ハッシュ生成処理(全部大文字になるやつ)
			String Password = DatatypeConverter.printHexBinary(bytes);//ハッシュ化したやつをPasswordに代入

			pStmt.setString(1, login_id);
			pStmt.setString(2, Password);//ここは変数。""いらない。
			pStmt.setString(3, name);
			pStmt.setString(4, birth_date);

			pStmt.executeUpdate();//これで実行

			pStmt.close();//ステートメントクローズ。

    	    } catch (SQLException | NoSuchAlgorithmException e) {
    	      e.printStackTrace();
			} finally {
				if (conn !=null) {
					try {
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();

					}
				}
			}
     	 	return;
	}

	public U_Beans findDetail(int id) {//detailを出力するため

		Connection conn = null;

		try {
			conn = DB_Manager.getConnection();//データベースに繋ぐ

			String sql = "SELECT id, login_id, name, birth_date, create_date, update_date FROM user WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);//いるやつ。
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();//rsで表を取得

			if (!rs.next()) { //SELECTを書くときにはnextがいる。これで行数を取り込むかんじ。
				return null; //次がなければnullを返す
			}
			int Id = rs.getInt("id");
			String loginId = rs.getString("login_id");//loginIdとは、login_idカラムの値を文字列として取り出したものですの意味
			String name = rs.getString("name");
			Date birthDate = rs.getDate("birth_date");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");
			U_Beans detail = new U_Beans(Id, loginId, name, birthDate, createDate, updateDate);
			return detail;

				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				} finally {
					if (conn !=null) {
						try {
						conn.close();
						} catch (SQLException e) {
						e.printStackTrace();
						return null;
						}
						}
				}
			}


	public void deleteUser(String id) {

		Connection conn = null;

    	 try {
    		conn = DB_Manager.getConnection();//データベースに繋ぐ

    		String sql = "DELETE FROM user WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);//ステートメント
			pStmt.setString(1, id);
			pStmt.executeUpdate();//これで実行

			pStmt.close();//ステートメントクローズ。

   	    } catch (SQLException e) {
   	      e.printStackTrace();
			} finally {
				if (conn !=null) {
					try {
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();

					}
				}
			}
    	 	return;
	}

	public void updateUser1(String id,String name, String password, String birth_date, String update_date) {//passも変える場合

		Connection conn = null;

   	 try {
   		conn = DB_Manager.getConnection();//データベースに繋ぐ

  		String sql = "UPDATE user SET name = ?, password = ? , birth_date = ?, update_date = now() WHERE id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);//ステートメント

			String source = password;
			Charset charset = StandardCharsets.UTF_8;//日本語にするやつ
			String algorithm = "MD5";//ハッシュアルゴリズム
			byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));//ハッシュ生成処理(全部大文字になるやつ)
			String Password = DatatypeConverter.printHexBinary(bytes);//ハッシュ化したやつをPasswordに代入

			pStmt.setString(1, name);
			pStmt.setString(2, Password);
			pStmt.setString(3, birth_date);
			pStmt.setString(4, id);

			pStmt.executeUpdate();//これで実行

			pStmt.close();//ステートメントクローズ。

  	    } catch (SQLException | NoSuchAlgorithmException e) {
  	      e.printStackTrace();
			} finally {
				if (conn !=null) {
					try {
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();

					}
				}
			}
   	 	return;
	}

	public void updateUser2(String id, String name, String birth_date, String update_date){//passをかえない場合
		Connection conn = null;

	   	 try {
	   		conn = DB_Manager.getConnection();//データベースに繋ぐ

	  		String sql = "UPDATE user SET name = ?, birth_date =?, update_date = now() WHERE id = ?";

				PreparedStatement pStmt = conn.prepareStatement(sql);//ステートメント


				pStmt.setString(1, "name");
				pStmt.setString(2, "birth_date");
				pStmt.setString(3, "id");

				pStmt.executeUpdate();//これで実行
				pStmt.close();//ステートメントクローズ。

	  	    } catch (SQLException e) {
	  	      e.printStackTrace();
				} finally {
					if (conn !=null) {
						try {
						conn.close();
						} catch (SQLException e) {
							e.printStackTrace();

						}
					}
				}
	   	 	return;
	}

}
