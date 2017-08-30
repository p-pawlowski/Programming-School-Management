package servlets.admin_panel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MySqlUserDao;
import dao.UserDao;

@WebServlet("/UserManagementDelete")
public class ServletUserManagementDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer userId = Integer.valueOf(request.getParameter("user_id"));

		request.setAttribute("userId", userId);
		getServletContext().getRequestDispatcher("/admin_panel/UserManagementDelete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String delete = request.getParameter("delete");
		if (delete.equals("tak")) {
			Integer userId = Integer.valueOf(request.getParameter("user_id"));
			UserDao userDao = new MySqlUserDao();
			userDao.delete(userId);
		}
		response.sendRedirect("UserManagement");
	}

}
