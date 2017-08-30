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
import entity.User;
import entity_extended.ExcerciseSolution;

@WebServlet("/UserDetail")
public class ServletUserDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDao userDao = new MySqlUserDao();
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();

		// Wczytanie uzytkownika o id = id;
		Integer userId = Integer.valueOf(request.getParameter("id"));
		User user = userDao.read(userId);

		// Wczytanie listy rozwiazan zadan uzytkownika
		List<ExcerciseSolution> eDBUList = excerciseDao.findAllExcerciseDoneByUser(user);

		request.setAttribute("user", user);
		request.setAttribute("eDBUList", eDBUList);
		getServletContext().getRequestDispatcher("/UserDetail.jsp").forward(request, response);

	}

}
