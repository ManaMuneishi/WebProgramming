package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout_Servlet
 */
@WebServlet("/Logout_Servlet")
public class Logout_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Logout_Servlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();//セッションスコープの。

		session.removeAttribute("userInfo");//arraylistからのremove()

		response.sendRedirect("Login_Servlet");//リダイレクト

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
