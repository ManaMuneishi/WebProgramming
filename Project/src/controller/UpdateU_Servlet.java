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

@WebServlet("/UpdateU_Servlet")
public class UpdateU_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateU_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			String Id = request.getParameter("id"); // idを取得
			int id = Integer.parseInt(Id);//idをintに直す

			U_Dao userDao = new U_Dao();
			U_Beans de = userDao.findDetail(id);

			request.setAttribute("detail", de);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UpdateU.jsp");
			dispatcher.forward(request, response);
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return;


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			try {

				String Id = request.getParameter("id");

				String pass1 = request.getParameter("pass1");
				String pass2 = request.getParameter("pass2");

				String name = request.getParameter("name");
				String birth_date = request.getParameter("birth");
				String update_date = request.getParameter("update_date");

				//デフォルトのパスは放っておく。いらない。

				//両方埋まっている場合＝passが正しいときかつpassがnotnull＝ passを変えるとき =全部かえるとき！
				if(!(pass1 .equals (null)) && pass1 .equals (pass2)) {// nullでイコールになるか

					U_Dao userDao1 = new U_Dao();
					userDao1.updateUser1(Id, name, pass1, birth_date, update_date); //処理
					response.sendRedirect("ListU_Servlet");//リダイレクト

				}else if(pass1.equals(null) && pass2.equals(null)){ //pass両方nullの時

					U_Dao userDao2 = new U_Dao();
					userDao2.updateUser2(Id, name, birth_date, update_date); //処理
					response.sendRedirect("ListU_Servlet");//リダイレクト

				}else{
					request.setAttribute("errMsg2", "入力された内容は正しくありません");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UpdateU.jsp");
					dispatcher.forward(request, response);
					return;
				}
				} catch (Exception e) {
				e.printStackTrace();
				}
	     	 	return;

	}
}
