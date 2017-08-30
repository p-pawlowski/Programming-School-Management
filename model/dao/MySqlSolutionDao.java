package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Excercise;
import entity.Solution;
import entity.User;
import entity_extended.UserExcerciseSolution;

public class MySqlSolutionDao implements SolutionDao {

	private static final String CREATE_SOLUTION_QUERY = "INSERT INTO solution(created, updated, description, excercise_id, user_id) VALUES (?,?,?,?,?)";
	private static final String READ_SOLUTION_QUERY = "Select * from solution where id = ?";
	private static final String UPDATE_SOLUTION_QUERY = "UPDATE	solution SET updated = ? , description = ? WHERE id = ?";

	static private final String FIND_ALL_USER_SOLUTION_QUERY = "SELECT * FROM solution, excercise WHERE solution.excercise_id = "
			+ "excercise.id AND solution.user_id = ? ORDER	BY	excercise_id ASC";

	static private final String FIND_LAST_SOLUTION = "SELECT * FROM excercise, solution, users WHERE solution.excercise_id = "
			+ "excercise.id AND solution.user_id = users.id ORDER BY solution.created DESC LIMIT ?";

	// KONSTRUKTOR wprowadzajacy dane do palaczenia z baza danych
	public MySqlSolutionDao() {

	}

	@Override
	public Solution read(Integer id) {
		Solution solution = new Solution();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(READ_SOLUTION_QUERY);) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					solution.setId(resultSet.getInt("id"));
					solution.setCreated(resultSet.getTimestamp("created"));
					solution.setUpdated(resultSet.getTimestamp("updated"));
					solution.setDescription(resultSet.getString("description"));
					solution.setExcerciseId(resultSet.getInt("excercise_id"));
					solution.setUserId(resultSet.getInt("user_id"));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return solution;

	}

	@Override
	public List<Solution> findAllUserSolution(User user) {
		List<Solution> userList = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_SOLUTION_QUERY);) {
			statement.setInt(1, user.getId());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Solution solutionToAdd = new Solution();
					solutionToAdd.setId(resultSet.getInt("id"));
					solutionToAdd.setCreated(resultSet.getTimestamp("created"));
					solutionToAdd.setUpdated(resultSet.getTimestamp("updated"));
					solutionToAdd.setDescription(resultSet.getString("solution.description"));
					solutionToAdd.setExcerciseId(resultSet.getInt("excercise_id"));
					solutionToAdd.setUserId(resultSet.getInt("user_id"));
					userList.add(solutionToAdd);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return userList;

	}

	@Override
	public List<UserExcerciseSolution> findLastSolution(Integer numberSolution) {
		List<UserExcerciseSolution> lastSolutionlist = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_LAST_SOLUTION);) {
			statement.setInt(1, numberSolution);
			try (ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {

					// Wczytanie
					User user = new User();
					user.setId(resultSet.getInt("users.id"));
					user.setName(resultSet.getString("users.name"));
					user.setSurname(resultSet.getString("users.surname"));
					user.setEmail(resultSet.getString("users.email"));
					user.setUserGroupId(resultSet.getInt("users.user_group_id"));

					// Wczytywanie zadania z Bazy danych
					Excercise excercise = new Excercise();
					excercise.setId(resultSet.getInt("excercise.id"));
					excercise.setTitle(resultSet.getString("excercise.title"));
					excercise.setDescription(resultSet.getString("excercise.description"));
					// excerciseDoneByUserList.add(excerciseToAdd);

					// wczytywanie rozwiazania uzytkownika z Bazy Danych
					Solution solution = new Solution();
					solution.setId(resultSet.getInt("solution.id"));
					solution.setCreated(resultSet.getTimestamp("solution.created"));
					solution.setUpdated(resultSet.getTimestamp("solution.updated"));
					solution.setDescription(resultSet.getString("solution.description"));
					solution.setExcerciseId(resultSet.getInt("solution.excercise_id"));
					solution.setUserId(resultSet.getInt("solution.user_id"));

					// dodawanie do tablicy Zadania i Rozwiazania do listy
					UserExcerciseSolution userExcerciseSolution = new UserExcerciseSolution();
					userExcerciseSolution.setUser(user);
					userExcerciseSolution.setExcercise(excercise);
					userExcerciseSolution.setSolution(solution);
					lastSolutionlist.add(userExcerciseSolution);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return lastSolutionlist;

	}

	@Override
	public Solution create(Solution solution) {
		try (Connection connection = DbUtil.getConn();
				PreparedStatement insertStm = connection.prepareStatement(CREATE_SOLUTION_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

			insertStm.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			insertStm.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			insertStm.setString(3, solution.getDescription());
			insertStm.setInt(4, solution.getExcerciseId());
			insertStm.setInt(5, solution.getUserId());
			int result = insertStm.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("Execute update returned " + result);
			}

			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					solution.setId(generatedKeys.getInt(1));
					return solution;
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

	@Override
	public void update(Solution solution) {
		try (Connection connection = DbUtil.getConn();
				PreparedStatement insertStm = connection.prepareStatement(UPDATE_SOLUTION_QUERY)) {

			insertStm.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			insertStm.setString(2, solution.getDescription());
			insertStm.setInt(3, solution.getId());
			insertStm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}

}
