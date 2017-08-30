package servlets.admin_panel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExcerciseDao;
import dao.MySqlExcerciseDao;
import entity.Excercise;

@WebServlet("/ExcerciseManagementAdd")
public class ServletExcerciseManagementAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/admin_panel/ExcerciseManagementAdd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		Excercise excercise = new Excercise();
		excercise.setTitle(title);
		excercise.setDescription(description);
		
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();
		excerciseDao.create(excercise);
	
		response.sendRedirect("ExcerciseManagement");
	}

}
