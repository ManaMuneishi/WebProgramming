package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SearchU_Dao;
import dao.U_Dao;
import model.U_Beans;


/**
 * Servlet implementation class ListU_Servlet
 */
@WebServlet("/ListU_Servlet")
public class ListU_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListU_Servlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				//ログインセッションがない場合、ログイン画面にリダイレクト。
				request.setCharacterEncoding("UTF-8");//日本語にする

				HttpSession session = request.getSession();//セッションスコープ
				Object user = session.getAttribute("userInfo");//

				if (user == null) {  //セッションがないとき= null
					response.sendRedirect("Login_Servlet");//ログインにリダイレクト
				return;//メソッド内で別の遷移先があるときは、最初の遷移のとこに必ずreturnを書こう。
				}

				U_Dao userDao= new U_Dao();//daoをインスタンス化
				List<U_Beans> userList = userDao.findAll();//userList(U_Beans型)を取得

				request.setAttribute("userList", userList);//userListという名前をさっきの一覧につける
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListU.jsp");//投げる
				dispatcher.forward(request, response);
				//ここは必ず空けよう。
				}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
		String Login_id = request.getParameter("login_id");
		String Name = request.getParameter("name");

		String birth1 = request.getParameter("birth1");

	    String birth2 = request.getParameter("birth2");

		if(!(Login_id .equals (null))) {//loginidのみで検索

			SearchU_Dao suDao1 = new SearchU_Dao();
			List<U_Beans> result1 = suDao1.searchByLoginId(Login_id); //処理

			request.setAttribute("Result", result1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/SearchListU.jsp");
			dispatcher.forward(request, response);
			return;

		}else if(!(Name .equals (null))) {//nameのみで検索

			SearchU_Dao suDao2 = new SearchU_Dao();
			List<U_Beans> result2 = suDao2.searchByLoginId(Login_id); //処理

			request.setAttribute("Result", result2);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/SearchListU.jsp");
			dispatcher.forward(request, response);
			return;

		}else if( !(birth1 .equals(null)) && (birth1 .before(birth2)) ) {//生年月日のみで検索

			SearchU_Dao suDao3 = new SearchU_Dao();
			List<U_Beans> result3 = suDao3.searchByLoginId(Login_id); //処理

			request.setAttribute("Result", result3);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/SearchListU.jsp");
			dispatcher.forward(request, response);
			return;

		}else {//その他全部
			request.setAttribute("errResult", "入力された内容では検索できません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListU.jsp");
			dispatcher.forward(request, response);
			return;
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
 	 	return;
	}
}
