package controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;//セッションスコープ用import
import javax.xml.bind.DatatypeConverter;

import dao.U_Dao;
import model.U_Beans;

/**
 * Servlet implementation class Login_Servlet
 */
@WebServlet("/Login_Servlet")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//日本語にする

		HttpSession session = request.getSession();
		Object user = session.getAttribute("userInfo");//getattributeはobjectのクラスにいる。
		if (user != null) {
			response.sendRedirect("ListU_Servlet");
			return;  //ここは一行あけるかreturnをかく。どちらもしていないと、続いていることになってエラーになる。
		}
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");// リクエストパラメータの文字コードを指定(日本語にする)
        try {
		String loginId = request.getParameter("loginId"); // リクエストパラメータの入力項目を取得(送信されるデータを定義）

		//admin以外のpasswordをハッシュ化して送る。
		if(!(loginId .equals("admin"))) {
		Charset charset = StandardCharsets.UTF_8;//日本語にするやつ

		String source =  request.getParameter("password");
		String algorithm = "MD5";//ハッシュアルゴリズム
		byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));//ハッシュ生成処理(全部大文字になるやつ)
		String Password = DatatypeConverter.printHexBinary(bytes);//ハッシュ化したやつをPasswordに代入

		U_Dao UserDao = new U_Dao(); // リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		U_Beans user = UserDao.findByLoginId(loginId, Password);

			//該当なし
			if (user == null) {
				request.setAttribute("errMsg", "ログインに失敗しました。"); // リクエストスコープにエラーメッセージをセット

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");// ログインjspにフォワード
				dispatcher.forward(request, response);
				return;//これ大事。
			}
			//該当あり
			// セッションにユーザの情報をセット
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", user);

			// ユーザ一覧のサーブレットにリダイレクト
			response.sendRedirect("ListU_Servlet");

		} else {
			String Password = request.getParameter("password");

			U_Dao UserDao = new U_Dao(); // リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
			U_Beans user = UserDao.findByLoginId(loginId, Password);

			//該当なし
			if (user == null) {
				request.setAttribute("errMsg", "ログインに失敗しました。"); // リクエストスコープにエラーメッセージをセット

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");// ログインjspにフォワード
				dispatcher.forward(request, response);
				return;//これ大事。
			}

			//該当あり
			// セッションにユーザの情報をセット
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", user);

			// ユーザ一覧のサーブレットにリダイレクト
			response.sendRedirect("ListU_Servlet");
		}
        } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
		}
	}
}
