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

@WebServlet("/ExcerciseManagementEdit")
public class ServletExcerciseManagementEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer excerciseId = Integer.valueOf(request.getParameter("excercise_id"));
		
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();
		Excercise excercise = excerciseDao.read(excerciseId);
		
		request.setAttribute("excercise", excercise);
		getServletContext().getRequestDispatcher("/admin_panel/ExcerciseManagementEdit.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("excercise_id"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		Excercise excercise = new Excercise(id, title, description);
		
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();
		excerciseDao.update(excercise);
		
		response.sendRedirect("ExcerciseManagement");
	}

}
