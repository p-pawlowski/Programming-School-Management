package servlets.admin_panel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExcerciseDao;
import dao.MySqlExcerciseDao;
import entity.Excercise;

@WebServlet("/ExcerciseManagement")
public class ServletExcerciseManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();
		List<Excercise> list = excerciseDao.findAllExcercise();

		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/admin_panel/ExcerciseManagement.jsp").forward(request, response);
	}

}
