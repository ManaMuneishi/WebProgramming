package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.U_Dao;
import model.U_Beans;

/**
 * Servlet implementation class DetailU_S
 */
@WebServlet("/DetailU_S")
public class DetailU_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public DetailU_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する
		// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード
				request.setCharacterEncoding("UTF-8");

				HttpSession session = request.getSession();
				Object user = session.getAttribute("userInfo");//
				if (user == null) {
					response.sendRedirect("Login_Servlet");//セッションないときはログイン画面にリダイレクト
					return;
				}

				try {
					String Id = request.getParameter("id"); // idを取得
					int id = Integer.parseInt(Id);//idをintに直す

					U_Dao userDao = new U_Dao();
					U_Beans de = userDao.findDetail(id);

					request.setAttribute("detail", de);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/DetailU.jsp");
					dispatcher.forward(request, response);
					return;

				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}

