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
 * Servlet implementation class DeleteU_S
 */
@WebServlet("/DeleteU_S")
public class DeleteU_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteU_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			String Id = request.getParameter("id"); // idを取得
			int id = Integer.parseInt(Id);//idをintに直す

			U_Dao userDao = new U_Dao();
			U_Beans de = userDao.findDetail(id);

			request.setAttribute("detail", de); //detailのdaoで取得したうち、jspではログインidだけ表示する
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/DeleteU.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String Id = request.getParameter("id"); // idを取得
			int id = Integer.parseInt(Id);//idをintに直す

			U_Dao userDao = new U_Dao();
			userDao.deleteUser(id);

			response.sendRedirect("ListU_Servlet");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
}
