package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExcerciseDao;
import dao.MySqlExcerciseDao;
import dao.MySqlSolutionDao;
import dao.MySqlUserDao;
import dao.SolutionDao;
import dao.UserDao;
import entity.Excercise;
import entity.Solution;
import entity.User;

@WebServlet("/user-solution")
public class ServletUserSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Wczytywanie parametrow z url
		Integer userId = Integer.valueOf(request.getParameter("user_id"));
		Integer excerciseId = Integer.valueOf(request.getParameter("excercise_id"));
		Integer solutionId = Integer.valueOf(request.getParameter("solution_id"));

		// Inicjowanie Dao
		SolutionDao solutionDao = new MySqlSolutionDao();
		UserDao userDao = new MySqlUserDao();
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();

		// Wczytywanie z bazy danych
		User user = userDao.read(userId);
		Solution solution = solutionDao.read(solutionId);
		Excercise excercise = excerciseDao.read(excerciseId);

		// przekazanie obiektow w zadaniu
		request.setAttribute("user", user);
		request.setAttribute("excercise", excercise);
		request.setAttribute("solution", solution);
		getServletContext().getRequestDispatcher("/UserSolution.jsp").forward(request, response);

	}

}
