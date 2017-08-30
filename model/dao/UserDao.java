package dao;

import java.util.List;

import entity.Excercise;
import entity.User;
import entity_extended.UserSolution;
import entity_extended.UserUserGroup;

public interface UserDao {

	User create(User user);

	User read(Integer userId);
	
	User readByEmail(String email);
	
	void update(User user); 
	
	void delete(Integer userId);
	
	List<User> findAll();
	
	List<User> findAllUserBelongToUserGruop(Integer userGroupId);
	
	//Lista uzytkownikow ktorzy rozwiazali zadanie
	List<UserSolution> findAllUserWhoAddSolution(Excercise excercise);
	
	//Lista uzytkownikow z przydzielonymi grupami
	List<UserUserGroup> findAllUserUserGroup();

}
