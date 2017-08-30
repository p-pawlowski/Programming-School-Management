package servlets.admin_panel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MySqlUserGroupDao;
import dao.UserGroupDao;
import entity.UserGroup;


@WebServlet("/GroupManagementAdd")
public class ServletGroupManagementAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/admin_panel/GroupManagementAdd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		UserGroup userGroup = new UserGroup();
		userGroup.setName(name);

		UserGroupDao userGroupDao = new MySqlUserGroupDao();
		userGroupDao.create(userGroup);
	
		response.sendRedirect("GroupManagement");
	}

}
