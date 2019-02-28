package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;//セッションスコープ用import

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

		if (user != null) { //セッションが残ってるときはそのままリダイレクトする
			response.sendRedirect("ListU_Servlet");
			return;  //ここは一行あけるかreturnをかく。どちらもしていないと、続いていることになってエラーになる。
		}

		//以上の内容をloginページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");// リクエストパラメータの文字コードを指定(日本語にする)

		String loginId = request.getParameter("loginId"); // リクエストパラメータの入力項目を取得(送信されるデータを定義）
		String password = request.getParameter("password");

		U_Dao UserDao = new U_Dao(); // リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		U_Beans user = UserDao.findByLoginId(loginId, password);

		/** テーブルに該当のデータが見つからなかった場合 **/
		if (user == null) {
			request.setAttribute("errMsg", "ログインに失敗しました。"); // リクエストスコープにエラーメッセージをセット

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");// ログインjspにフォワード
			dispatcher.forward(request, response);
			return;//これ大事。
		}

		/** テーブルに該当のデータが見つかった場合 **/
		// セッションにユーザの情報をセット
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", user);

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("ListU_Servlet");
	}

}
