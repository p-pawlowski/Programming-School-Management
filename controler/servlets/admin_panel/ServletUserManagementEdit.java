package servlets.admin_panel;

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


@WebServlet("/UserManagementEdit")
public class ServletUserManagementEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UserGroupDao userGroupDao = new MySqlUserGroupDao();
		List<UserGroup> list = userGroupDao.findAllUserGroup();
		request.setAttribute("list", list);
			
		Integer userId = Integer.valueOf(request.getParameter("user_id"));
		UserDao userDao = new MySqlUserDao();
		User user = userDao.read(userId);
		
		request.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/admin_panel/UserManagementEdit.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("user_id"));
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		Integer userGroupId = Integer.valueOf(request.getParameter("user_group_id"));

		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);
		user.setUserGroupId(userGroupId);
		UserDao userDao = new MySqlUserDao();
		userDao.update(user);

		response.sendRedirect("UserManagement");
	}

}
