package servlets.admin_panel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MySqlUserDao;
import dao.UserDao;
import entity_extended.UserUserGroup;


@WebServlet("/UserManagement")
public class ServletUserManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new MySqlUserDao();
		List<UserUserGroup> list = userDao.findAllUserUserGroup();
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/admin_panel/UserManagement.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	;
	}

}
