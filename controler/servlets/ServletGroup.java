package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MySqlUserGroupDao;
import dao.UserGroupDao;
import entity.UserGroup;

@WebServlet("/Group")
public class ServletGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserGroupDao userGroupDao = new MySqlUserGroupDao();
		List<UserGroup> list = userGroupDao.findAllUserGroup();
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/Group.jsp").forward(request, response);
	}

}
