package dao;

import java.util.List;

import entity.Solution;
import entity.User;
import entity_extended.UserExcerciseSolution;


public interface SolutionDao {
	
	Solution create (Solution solution);
	
	//wczytanie rozwiazania po id
	Solution read(Integer id);
	
	void update (Solution solution);
	
	//lista rizwuazan danego uzytkownika
	List<Solution> findAllUserSolution(User user);
	
	//lista ostanich rozwiazan
	List<UserExcerciseSolution> findLastSolution(Integer numberSolution);
	
	

}
