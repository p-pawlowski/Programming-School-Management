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


@WebServlet("/GroupManagementEdit")
public class ServletGroupManagementEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Integer userGroupId = Integer.valueOf(request.getParameter("user_group_id"));
		
		UserGroupDao userGroupDao = new MySqlUserGroupDao();
		
		UserGroup userGroup = userGroupDao.read(userGroupId);
		
		request.setAttribute("userGroup", userGroup);
		getServletContext().getRequestDispatcher("/admin_panel/GroupManagementEdit.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("user_group_id"));
		String name = request.getParameter("name");
		UserGroup userGroup = new UserGroup(id,name);
		
		UserGroupDao userGroupDao = new MySqlUserGroupDao();
		userGroupDao.update(userGroup);
	
		response.sendRedirect("GroupManagement");
	}

}
