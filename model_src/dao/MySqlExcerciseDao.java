package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.Excercise;
import entity.Solution;
import entity.User;
import entity_extended.ExcerciseSolution;

public class MySqlExcerciseDao implements ExcerciseDao {

	private static final String CREATE_EXCERCISE_QUERY = "INSERT INTO excercise(title, description) VALUES (?,?)";
	private static final String READ_EXCERCISE_QUERY = "Select * from excercise where id = ?";
	private static final String UPDATE_EXCERCISE_QUERY = "UPDATE excercise SET title = ? , description = ? WHERE	id = ?";
	private static final String DELETE_EXCERCISE_QUERY = "DELETE FROM excercise WHERE id = ?";
	static private final String FIND_ALL_USER_EXCERCISE_QUERY = "SELECT * FROM excercise, solution WHERE solution.excercise_id = "
			+ "excercise.id AND solution.user_id = ? ORDER	BY	excercise_id ASC";
	static private final String FIND_ALL_EXCERCISE_QUERY = "SELECT * FROM excercise";
	static private final String FIND_ALL_USER_EXCERCISE_LEFT_QUERY = "SELECT * FROM excercise LEFT JOIN solution ON solution.excercise_id = "
			+ "excercise.id AND solution.user_id = ? ORDER	BY	excercise_id ASC";

	// KONSTRUKTOR
	public MySqlExcerciseDao() {
	}

	// DODANIE NOWEGO ZADANIA
	@Override
	public Excercise create(Excercise excercise) {
		;
		try (Connection connection = DbUtil.getConn();
				PreparedStatement insertStm = connection.prepareStatement(CREATE_EXCERCISE_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			insertStm.setString(1, excercise.getTitle());
			insertStm.setString(2, excercise.getDescription());
			int result = insertStm.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("Execute update returned " + result);
			}

			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					excercise.setId(generatedKeys.getInt(1));
					return excercise;
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

	//WCZYTANIE ZADANIA PO ID
	@Override
	public Excercise read(Integer id) {
		Excercise excercise = new Excercise();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(READ_EXCERCISE_QUERY);) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					excercise.setId(resultSet.getInt("id"));
					excercise.setTitle(resultSet.getString("title"));
					excercise.setDescription(resultSet.getString("description"));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return excercise;

	}

	//EDYCJA ZADANIA
	@Override
	public void update(Excercise excercise) {
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EXCERCISE_QUERY);) {
			statement.setInt(3, excercise.getId());
			statement.setString(1, excercise.getTitle());
			statement.setString(2, excercise.getDescription());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

	}
	
	//USUWANIE ZADANIA PO ID
	@Override
	public void delete(Integer excerciseId){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(DELETE_EXCERCISE_QUERY);) {
			statement.setInt(1, excerciseId);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}

	//LISTA ZADAN WYKONANYCH PRZEZ UZYTKOWNIKA
	public List<Excercise> findAllUserExcercise(User user) {
		List<Excercise> userExcerciseList = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_EXCERCISE_QUERY);) {
			statement.setInt(1, user.getId());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Excercise excerciseToAdd = new Excercise();
					excerciseToAdd.setId(resultSet.getInt("id"));
					excerciseToAdd.setDescription(resultSet.getString("excercise.title"));
					excerciseToAdd.setDescription(resultSet.getString("excercise.description"));
					userExcerciseList.add(excerciseToAdd);
				}

			}

		} catch (Exception e) {
			System.out.println("Cos sie nie powiodło");
		}
		return userExcerciseList;

	}

	//LISTA ZADAN WRAZ Z ROZWIAZANIAMI WYKONANA PRZEZ UŻYTKOWNIKA
	@Override
	public List<ExcerciseSolution> findAllExcerciseDoneByUser(User user) {
		List<ExcerciseSolution> excerciseDoneByUserList = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_EXCERCISE_QUERY);) {
			statement.setInt(1, user.getId());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					// wczytywanie rozwiazania uzytkownika z Bazy Danych
					Solution solutionToAdd = new Solution();
					solutionToAdd.setId(resultSet.getInt("solution.id"));
					solutionToAdd.setCreated(resultSet.getTimestamp("created"));
					solutionToAdd.setUpdated(resultSet.getTimestamp("updated"));
					solutionToAdd.setDescription(resultSet.getString("solution.description"));
					solutionToAdd.setExcerciseId(resultSet.getInt("excercise_id"));
					solutionToAdd.setUserId(resultSet.getInt("user_id"));

					// Wczytywanie rozwiazanego przez uzytkownika zadania z bazy
					// danych
					Excercise excerciseToAdd = new Excercise();
					excerciseToAdd.setId(resultSet.getInt("excercise.id"));
					excerciseToAdd.setTitle(resultSet.getString("excercise.title"));
					excerciseToAdd.setDescription(resultSet.getString("excercise.description"));
					// excerciseDoneByUserList.add(excerciseToAdd);

					// dodawanie do tablicy Zadania i Rozwiazania do listy
					ExcerciseSolution excerciseDBU = new ExcerciseSolution();
					excerciseDBU.setExcercise(excerciseToAdd);
					excerciseDBU.setSolution(solutionToAdd);
					excerciseDoneByUserList.add(excerciseDBU);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return excerciseDoneByUserList;

	}

	//LISTA WSZYSTKICH ZADAN
	@Override
	public List<Excercise> findAllExcercise() {
		List<Excercise> list = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_EXCERCISE_QUERY);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {

				// Wczytywanie rozwiazanego przez uzytkownika zadania z bazy
				// danych
				Excercise excerciseToAdd = new Excercise();
				excerciseToAdd.setId(resultSet.getInt("id"));
				excerciseToAdd.setTitle(resultSet.getString("title"));
				excerciseToAdd.setDescription(resultSet.getString("description"));
				list.add(excerciseToAdd);

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return list;

	}
	
	//LISTA ZADAN WRAZ Z ROZWIAZANIAMI WYKONANA PRZEZ UŻYTKOWNIKA
	@Override
	public List<ExcerciseSolution> findAllExcerciseDoneByUserLeft(User user) {
		List<ExcerciseSolution> excerciseDoneByUserList = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_EXCERCISE_LEFT_QUERY);) {
			statement.setInt(1, user.getId());
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					// wczytywanie rozwiazania uzytkownika z Bazy Danych
					Solution solutionToAdd = new Solution();
					solutionToAdd.setId(resultSet.getInt("solution.id"));
					solutionToAdd.setCreated(resultSet.getTimestamp("created"));
					solutionToAdd.setUpdated(resultSet.getTimestamp("updated"));
					solutionToAdd.setDescription(resultSet.getString("solution.description"));
					solutionToAdd.setExcerciseId(resultSet.getInt("excercise_id"));
					solutionToAdd.setUserId(resultSet.getInt("user_id"));

					// Wczytywanie rozwiazanego przez uzytkownika zadania z bazy
					// danych
					Excercise excerciseToAdd = new Excercise();
					excerciseToAdd.setId(resultSet.getInt("excercise.id"));
					excerciseToAdd.setTitle(resultSet.getString("excercise.title"));
					excerciseToAdd.setDescription(resultSet.getString("excercise.description"));
					// excerciseDoneByUserList.add(excerciseToAdd);

					// dodawanie do tablicy Zadania i Rozwiazania do listy
					ExcerciseSolution excerciseDBU = new ExcerciseSolution();
					excerciseDBU.setExcercise(excerciseToAdd);
					excerciseDBU.setSolution(solutionToAdd);
					excerciseDoneByUserList.add(excerciseDBU);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return excerciseDoneByUserList;

	}

}
