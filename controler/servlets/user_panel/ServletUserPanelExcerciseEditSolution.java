package servlets.user_panel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ExcerciseDao;
import dao.MySqlExcerciseDao;
import dao.MySqlSolutionDao;
import dao.SolutionDao;
import entity.Excercise;
import entity.Solution;

@WebServlet("/UserPanelExcerciseEditSolution")
public class ServletUserPanelExcerciseEditSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer excerciseId = Integer.valueOf(request.getParameter("excercise_id"));
		
		Integer solutionId = Integer.valueOf(request.getParameter("solution_id"));
		
		SolutionDao solutionDao = new MySqlSolutionDao();
		Solution solution = solutionDao.read(solutionId);
		
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();
		Excercise excercise = excerciseDao.read(excerciseId);
		
		request.setAttribute("solution", solution);
		request.setAttribute("excercise", excercise);
		
		getServletContext().getRequestDispatcher("/user_panel/UserPanelExcerciseEditSolution.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String description = request.getParameter("description");
		Integer solutionId = Integer.valueOf(request.getParameter("solution_id"));
		
		Solution solution = new Solution();
		solution.setId(solutionId);
		solution.setDescription(description);
		
		SolutionDao solutionDao = new MySqlSolutionDao();
		solutionDao.update(solution);

		response.sendRedirect("UserPanelExcercise");
	}

}
