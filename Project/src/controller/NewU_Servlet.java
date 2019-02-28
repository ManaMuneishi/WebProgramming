package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.U_Dao;

@WebServlet("/NewU_Servlet")
public class NewU_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NewU_Servlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/NewU.jsp");//フォワード
		dispatcher.forward(request, response);

		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		//stringの誕生日をDate型に変換する←しなくていい。stringから勝手にdateへ変わる.
		//stringでしかパラメータ受け取れない←受け取らなくていい
		try {
			String pass1 = request.getParameter("password1");
			String pass2 = request.getParameter("password2");

			if(pass1 .equals (pass2)) {
				String login_id = request.getParameter("login_id"); // リクエストパラメータの入力項目を取得(送信されるデータを定義）
				String name = request.getParameter("name");
				String birth_date = request.getParameter("birth_date");
				String create_date= request.getParameter("create_date");
				String update_date= request.getParameter("update_date");

				U_Dao userDao = new U_Dao(); // リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
				userDao.insertInfo(login_id, pass1, name, birth_date, create_date, update_date);
				response.sendRedirect("ListU_Servlet");

			}else{
				request.setAttribute("errMsg2", "入力された内容は正しくありません");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/NewU.jsp");
				dispatcher.forward(request, response);
				return;
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
     	 	return;
	}
}


