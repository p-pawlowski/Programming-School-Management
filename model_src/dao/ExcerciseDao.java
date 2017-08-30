package dao;

import java.util.List;

import entity.Excercise;
import entity.User;
import entity_extended.ExcerciseSolution;

public interface ExcerciseDao {


	
	//Wczytanie Zadania
	Excercise create(Excercise excercise);
	
	Excercise read(Integer id);
	
	void update(Excercise excercise);
	
	void delete(Integer excerciseId);
	
	//Lista wszystkich zadan rozwiazanych przez uzytkownika
	List<Excercise> findAllUserExcercise(User user);
	
	//Lista wszystkich zadan wraz z rozwiazaniami
	List<ExcerciseSolution> findAllExcerciseDoneByUser(User user);
	
	List<Excercise> findAllExcercise();

	//Lista wszystkich zadan wraz z rozwiazaniami
	List<ExcerciseSolution> findAllExcerciseDoneByUserLeft(User user);
	
}
