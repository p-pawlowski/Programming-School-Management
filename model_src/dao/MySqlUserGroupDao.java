package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entity.UserGroup;

public class MySqlUserGroupDao implements UserGroupDao {

	private static final String CREATE_USER_GROUP_QUERY = "INSERT INTO user_group(name) VALUES (?)";
	private static final String READ_USER_GROUP_QUERY = "Select * from user_group where id = ?";
	private static final String UPDATE_USER_GROUP_QUERY = "UPDATE user_group SET name = ? WHERE	id = ?";
	private static final String DELETE_USER_GROUP_QUERY = "DELETE FROM user_group WHERE id = ?";
	static private final String FIND_ALL_USER_GROUP_QUERY = "SELECT * FROM user_group ORDER BY ID";

	// KONSTRUKTOR wprowadzajacy dane do palaczenia z baza danych
	public MySqlUserGroupDao() {

	}
	
	// DODANIE NOWEGO ZADANIA
	@Override
	public UserGroup create(UserGroup userGroup)  {
		
		try (Connection connection = DbUtil.getConn();
				PreparedStatement insertStm = connection.prepareStatement(CREATE_USER_GROUP_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			insertStm.setString(1, userGroup.getName());
			int result = insertStm.executeUpdate();

			if (result != 1) {
				throw new RuntimeException("Execute update returned " + result);
			}

			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					userGroup.setId(generatedKeys.getInt(1));
					return userGroup;
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
	public UserGroup read(Integer Id) {
		UserGroup userGroup = new UserGroup();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(READ_USER_GROUP_QUERY);) {
			statement.setInt(1, Id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					userGroup.setId(resultSet.getInt("id"));
					userGroup.setName(resultSet.getString("name"));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
		return userGroup;

	}
	
	//EDYCJA ZADANIA
	@Override
	public void update(UserGroup userGroup) {
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_GROUP_QUERY);) {
			statement.setInt(2, userGroup.getId());
			statement.setString(1, userGroup.getName());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}

	}
	
	//USUWANIE ZADANIA PO ID
	@Override
	public void delete(Integer id){
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_GROUP_QUERY);) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cos sie nie powiodło");
		}
	}

	@Override
	public List<UserGroup> findAllUserGroup() {
		List<UserGroup> list = new ArrayList<>();
		try (Connection connection = DbUtil.getConn();
				PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_GROUP_QUERY);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				UserGroup userGroup = new UserGroup();
				userGroup.setId(resultSet.getInt("id"));
				userGroup.setName(resultSet.getString("name"));
				list.add(userGroup);
			}

		} catch (Exception e) {
			System.out.println("Cos sie nie powiodło");
		}
		return list;

	}



}
