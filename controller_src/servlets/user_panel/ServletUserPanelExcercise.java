package servlets.user_panel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ExcerciseDao;
import dao.MySqlExcerciseDao;
import dao.MySqlUserDao;
import dao.UserDao;
import entity.User;
import entity_extended.ExcerciseSolution;

@WebServlet("/UserPanelExcercise")
public class ServletUserPanelExcercise extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession();
		User user = (User)sess.getAttribute("user");
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();
		List<ExcerciseSolution> list = excerciseDao.findAllExcerciseDoneByUserLeft(user);
		request.setAttribute("list", list);
		
		getServletContext().getRequestDispatcher("/user_panel/UserPanelExcercise.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = "";
		UserDao userDao = new MySqlUserDao();
		email = request.getParameter("email");
		User user = userDao.readByEmail(email);
		if (user.getId() == null) {
			response.sendRedirect("UserPanel");
			return;
		}
		ExcerciseDao excerciseDao = new MySqlExcerciseDao();
		List<ExcerciseSolution> list = excerciseDao.findAllExcerciseDoneByUserLeft(user);
		request.setAttribute("list", list);

		HttpSession sess = request.getSession();
		sess.setAttribute("user", user);
		request.setAttribute("user", user);

		getServletContext().getRequestDispatcher("/user_panel/UserPanelExcercise.jsp").forward(request, response);

	}

}
