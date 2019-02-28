package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

				try {
					String id = request.getParameter("Id"); // idを取得
					int Id = Integer.parseInt(id);//idをintに直す

					U_Dao userDao = new U_Dao();
					U_Beans de = userDao.findDetail(Id);

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

