package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExcerciseDao;
import dao.MySqlExcerciseDao;
import dao.MySqlUserDao;
import dao.UserDao;
import entity.Excercise;
import entity_extended.UserSolution;


@WebServlet("/ExcerciseDetail")
public class ServletExcerciseDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer excerciseId = Integer.valueOf(request.getParameter("excercise_id"));
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();
		Excercise excercise = excerciseDao.read(excerciseId);
		
		UserDao userDao = new MySqlUserDao();
		List<UserSolution> userSolutionlist = userDao.findAllUserWhoAddSolution(excercise);
		
		request.setAttribute("userSolutionList", userSolutionlist);
		request.setAttribute("excercise", excercise);
		getServletContext().getRequestDispatcher("/ExcerciseDetail.jsp").forward(request, response);
	}

}
