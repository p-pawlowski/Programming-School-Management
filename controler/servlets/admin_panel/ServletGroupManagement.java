package servlets.admin_panel;

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

@WebServlet("/GroupManagement")
public class ServletGroupManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserGroupDao userGroupDao = new MySqlUserGroupDao();
		List<UserGroup> list = userGroupDao.findAllUserGroup();
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/admin_panel/GroupManagement.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
