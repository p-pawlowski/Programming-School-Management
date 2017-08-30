package servlets.admin_panel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ExcerciseDao;
import dao.MySqlExcerciseDao;

@WebServlet("/ExcerciseManagementDelete")
public class ServletExcerciseManagementDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer excerciseId = Integer.valueOf(request.getParameter("excercise_id"));

		request.setAttribute("excerciseId", excerciseId);
		getServletContext().getRequestDispatcher("/admin_panel/ExcerciseManagementDelete.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String delete = request.getParameter("delete");
		if (delete.equals("tak")){
			Integer excerciseId = Integer.valueOf(request.getParameter("excercise_id"));
			ExcerciseDao excerciseDao = new MySqlExcerciseDao();
			excerciseDao.delete(excerciseId);			
		}
		response.sendRedirect("ExcerciseManagement");
	}

}
