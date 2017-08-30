package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MySqlSolutionDao;
import dao.SolutionDao;
import entity_extended.UserExcerciseSolution;

@WebServlet("/MainPage")
public class ServletMainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer numberSolution = Integer.valueOf(getServletContext().getInitParameter("numberSolution"));
		
		SolutionDao solutionDao = new MySqlSolutionDao();
		List<UserExcerciseSolution> list = solutionDao.findLastSolution(numberSolution);
		
		request.setAttribute("list", list);
		getServletContext().getRequestDispatcher("/MainPage.jsp").forward(request, response);
	}

}
