package entity_extended;

import entity.Excercise;
import entity.Solution;

public class ExcerciseSolution {

	private Excercise excercise;
	private Solution solution;
	
	public ExcerciseSolution(Solution solution, Excercise excercise) {
		this.solution = solution;
		this.excercise = excercise;
	}
	
	public ExcerciseSolution() {
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

	@Override
	public String toString() {
		return "\nExcerciseDoneByUser: excercise id: + " + excercise.getId() + " solution id:" + solution.getId();
	}
	
	

}
