package servlets.admin_panel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MySqlUserGroupDao;
import dao.UserGroupDao;


@WebServlet("/GroupManagementDelete")
public class ServletGroupManagementDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer userGroupId = Integer.valueOf(request.getParameter("user_group_id"));

		request.setAttribute("userGroupId", userGroupId);
		getServletContext().getRequestDispatcher("/admin_panel/GroupManagementDelete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delete = request.getParameter("delete");
		if (delete.equals("tak")){
			Integer userGroupId = Integer.valueOf(request.getParameter("user_group_id"));
			UserGroupDao userGroupDao = new MySqlUserGroupDao();
			userGroupDao.delete(userGroupId);			
		}
		response.sendRedirect("GroupManagement");
	}

}
