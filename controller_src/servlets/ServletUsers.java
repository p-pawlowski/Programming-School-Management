package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MySqlUserDao;
import dao.MySqlUserGroupDao;
import dao.UserDao;
import dao.UserGroupDao;
import entity.User;
import entity.UserGroup;

@WebServlet("/Users")
public class ServletUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userGroupId = Integer.valueOf(request.getParameter("user_group_id"));

		UserDao userDao = new MySqlUserDao();
		List<User> list = userDao.findAllUserBelongToUserGruop(userGroupId);

		UserGroupDao userGroupDao = new MySqlUserGroupDao();
		UserGroup userGroup = userGroupDao.read(userGroupId);

		request.setAttribute("userGroup", userGroup);
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/Users.jsp").forward(request, response);
	}

}
