package entity_extended;


import entity.Excercise;
import entity.Solution;
import entity.User;

public class UserExcerciseSolution {
	
	
	private User user;
	private Excercise excercise;
	private Solution solution;
	
	
	public UserExcerciseSolution(Solution solution, Excercise excercise, User user) {
		this.user = user;
		this.solution = solution;
		this.excercise = excercise;
	}
	
	public UserExcerciseSolution() {
	}
	
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	public Excercise getExcercise() {
		return excercise;
	}
	public void setExcercise(Excercise excercise) {
		this.excercise = excercise;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserExcerciseSolution [solution=" + solution + ", excercise=" + excercise + ", user=" + user + "]\n";
	}

	
	
	

}