package entity_extended;

import entity.User;
import entity.UserGroup;

public class UserUserGroup {
	private User user;
	private UserGroup userGroup;

	public UserUserGroup() {
	}

	public UserUserGroup(User user, UserGroup userGroup) {
		this.user = user;
		this.userGroup = userGroup;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	@Override
	public String toString() {
		return "UserUserGroup [user=" + user + ", userGroup=" + userGroup + "]";
	}

}
