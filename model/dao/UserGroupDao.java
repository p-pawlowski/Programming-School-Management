package dao;

import java.util.List;

import entity.UserGroup;

public interface UserGroupDao {
	
	UserGroup create(UserGroup userGroup); 
	
	UserGroup read(Integer Id);
	
	void update(UserGroup userGroup);
	
	void delete (Integer Id);
	
	List<UserGroup> findAllUserGroup();

}
