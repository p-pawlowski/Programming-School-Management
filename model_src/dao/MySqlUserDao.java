package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Excercise;
import entity.Solution;
import entity.User;
import entity.UserGroup;
import entity_extended.UserSolution;
import entity_extended.UserUserGroup;

public class MySqlUserDao implements UserDao {

	// ZAPYTANIA SQL

	private static final String CREATE_USER_QUERY = "INSERT INTO users(name, surname,email,password,user_group_id) VALUES (?,?,?,?,?)";
	private static final String READ_USER_QUERY = "Select * from users where id = ?";
	private static final String READ_USER_BY_EMAIL_QUERY = "Select * from users where email = ?";
	private static final String UPDATE_USER_QUERY = "UPDATE	users SET name = ? , surname = ?, email = ?, password = ?, user_group_id =? WHERE	id = ?";
	private static final String DELETE_USER_QUERY = "DELETE FROM users where id = ?";
	private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
	
	static private final String FIND_ALL_USER_WHO_MADE_EXCERCISE = 
			"SELECT * FROM users, solution WHERE solution.user_id = "
			+ "users.id AND solution.excercise_id = ? ORDER	BY	solution.created ASC";
	
	static private final String FIND_ALL_USER_BELONG_TO_USER_GROUP = 
			"SELECT * FROM users, user_group WHERE user_group.id = "
			+ "users.user_group_id AND user_group.id = ? ORDER	BY users.id ASC";
	
	static private final String FIND_ALL_USER_USER_GROUP = 
			"SELECT * FROM users LEFT JOIN user_group ON users.user_group_id = "
			+ "user_group.id  ORDER	BY	user_group.id ASC";

	// KONSTRUKTOR
	public MySqlUserDao() {
	}

	// DODANIE NOWEGO UZYTKOWNIKA
	@Override
	public User create(User user) {
		try (Connection connection = DbUtil.getConn();
				PreparedStatement insertStm = connection.prepareStatement(CREATE_USER_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			insertStm.setString(1, user.getName());
			insertStm.setString(2, user.getSurname());
			insertStm.setString(3, user.getEmail());
			insertStm.setString(4, user.getPassword());
			insertStm.setInt(5, user.getUserGroupId());
			int result = insertStm.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("Execute update returned " + result);
			}

			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					user.setId(generatedKeys.getInt(1));
					return user;
				} else {
					throw new RuntimeException("Generated key was not found");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return null;
	}

	public User read(Integer userId) {
		User user = new User();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);
		) {
			statement.setInt(1, userId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setSurname(resultSet.getString("surname"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setUserGroupId(resultSet.getInt("user_group_id"));									
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return user;

	}
	
	@Override
	public User readByEmail(String email) {
		User user = new User();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(READ_USER_BY_EMAIL_QUERY);
		) {
			statement.setString(1, email);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					user.setId(resultSet.getInt("id"));
					user.setName(resultSet.getString("name"));
					user.setSurname(resultSet.getString("surname"));
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setUserGroupId(resultSet.getInt("user_group_id"));									
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return user;

	}
	
	//USUWANIE ZADANIA PO ID
		@Override
		public void delete(Integer userId){
			try (Connection connection = DbUtil.getConn();
					PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);) {
				statement.setInt(1, userId);
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Cos sie nie powiodło");
			}
		}

	// LISTA WSZYSTKICH UZYTKOWNIKOW
	@Override
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS_QUERY);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				User userToAdd = new User();
				userToAdd.setId(resultSet.getInt("id"));
				userToAdd.setName(resultSet.getString("name"));
				userToAdd.setEmail(resultSet.getString("email"));
				userToAdd.setPassword(resultSet.getString("password"));
				userToAdd.setUserGroupId(resultSet.getInt("user_group_id"));
				userList.add(userToAdd);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return userList;

	}

	// UPDATE
	@Override
	public void update(User user) {
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);) {
			statement.setInt(6, user.getId());
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getUserGroupId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

	}
	
	///Lista uzytkownikow ktorzy rozwiazali zadanie
	@Override
	public List<UserSolution> findAllUserWhoAddSolution(Excercise excercise) {
		List<UserSolution> userSolutionList = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_WHO_MADE_EXCERCISE);
		) {
			statement.setInt(1, excercise.getId());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					//wczytywanie rozwiazania uzytkownika z Bazy Danych
					Solution solution = new Solution();
					solution.setId(resultSet.getInt("solution.id"));
					solution.setCreated(resultSet.getTimestamp("created"));
					solution.setUpdated(resultSet.getTimestamp("updated"));
					solution.setDescription(resultSet.getString("solution.description"));
					solution.setExcerciseId(resultSet.getInt("excercise_id"));
					solution.setUserId(resultSet.getInt("user_id"));
					
					//Wczytanie uzytkownika
					User user = new User();
					user.setId(resultSet.getInt("users.id"));
					user.setName(resultSet.getString("users.name"));
					user.setSurname(resultSet.getString("users.surname"));
					user.setEmail(resultSet.getString("users.email"));
					user.setUserGroupId(resultSet.getInt("users.user_group_id"));
					
					//dodawanie do tablicy Zadania i Rozwiazania do listy
					UserSolution userSolution = new UserSolution();
					userSolution.setUser(user);
					userSolution.setSolution(solution);
					userSolutionList.add(userSolution);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return userSolutionList;

	}

	@Override
	public List<User> findAllUserBelongToUserGruop(Integer userGroupId){
		List<User> list = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_BELONG_TO_USER_GROUP);
		) {
			statement.setInt(1, userGroupId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					
					//Wczytanie uzytkownika
					User user = new User();
					user.setId(resultSet.getInt("users.id"));
					user.setName(resultSet.getString("users.name"));
					user.setSurname(resultSet.getString("users.surname"));
					user.setEmail(resultSet.getString("users.email"));
					user.setUserGroupId(resultSet.getInt("users.user_group_id"));
					
					//Dodanie uzytkownika do listy
					list.add(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return list;	
	
	}
	
	@Override
	public List<UserUserGroup> findAllUserUserGroup(){
		List<UserUserGroup> userUserGroupList = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_USER_GROUP);
				ResultSet resultSet = statement.executeQuery())
		 {
				while (resultSet.next()) {
					//wczytywanie rozwiazania uzytkownika z Bazy Danych
					UserGroup userGroup = new UserGroup();
					userGroup.setId(resultSet.getInt("user_group.id"));
					userGroup.setName(resultSet.getString("user_group.name"));
					
					//Wczytanie uzytkownika
					User user = new User();
					user.setId(resultSet.getInt("users.id"));
					user.setName(resultSet.getString("users.name"));
					user.setSurname(resultSet.getString("users.surname"));
					user.setEmail(resultSet.getString("users.email"));
					user.setUserGroupId(resultSet.getInt("users.user_group_id"));
					
					//dodawanie do listy
					UserUserGroup userUserGroup = new UserUserGroup();
					userUserGroup.setUser(user);
					userUserGroup.setUserGroup(userGroup);
					userUserGroupList.add(userUserGroup);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return userUserGroupList;

	}
	
}
