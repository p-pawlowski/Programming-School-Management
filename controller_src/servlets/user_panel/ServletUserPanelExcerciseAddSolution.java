package servlets.user_panel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.ExcerciseDao;
import dao.MySqlExcerciseDao;
import dao.MySqlSolutionDao;
import dao.SolutionDao;
import entity.Excercise;
import entity.Solution;
import entity.User;

@WebServlet("/UserPanelExcerciseAddSolution")
public class ServletUserPanelExcerciseAddSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer excerciseId = Integer.valueOf(request.getParameter("excercise_id"));

		ExcerciseDao excerciseDao = new MySqlExcerciseDao();
		Excercise excercise = excerciseDao.read(excerciseId);
		HttpSession sess = request.getSession();
		sess.setAttribute("excerciseId", excercise.getId());

		request.setAttribute("excercise", excercise);
		getServletContext().getRequestDispatcher("/user_panel/UserPanelExcerciseAddSolution.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");
		Integer excerciseId = (Integer) sess.getAttribute("excerciseId");
		String description = request.getParameter("description");

		Solution solution = new Solution();
		solution.setUserId(user.getId());
		solution.setExcerciseId(excerciseId);
		solution.setDescription(description);

		SolutionDao solutionDao = new MySqlSolutionDao();
		solutionDao.create(solution);
	
		response.sendRedirect("UserPanelExcercise");

	}

}
