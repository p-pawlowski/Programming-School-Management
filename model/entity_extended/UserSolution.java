package entity_extended;

import entity.Solution;
import entity.User;

public class UserSolution {

	private User user;
	private Solution solution;

	public UserSolution(User user, Solution solution) {
		this.user = user;
		this.solution = solution;
	}

	public UserSolution() {
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserSolution [user=" + user + ", solution=" + solution + "]";
	}

	
}
