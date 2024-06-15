package com.siam.services.impl;

import com.siam.enteties.User;
import com.siam.enteties.impl.DefaultUser;
import com.siam.services.UserManagementService;

import java.util.List;

import java.util.Arrays;
public class DefaultUserManagementService implements UserManagementService {

	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";

	private static final int DEFAULT_USERS_CAPACITY = 10;

	private static DefaultUserManagementService instance;
	private int lastAddedIndex;
	private User[] users;

	{
		users = new DefaultUser[10];
		lastAddedIndex = 0;
	}

	private DefaultUserManagementService() {
	}

	@Override
	public String registerUser(User user) {
		if(user == null) {
			return NO_ERROR_MESSAGE;
		}
		if(user.getEmail() == null || user.getEmail().length() == 0) {
			return EMPTY_EMAIL_ERROR_MESSAGE;
		}
		if(getUserByEmail(user.getEmail()) == null) {
			if(users.length <= lastAddedIndex) {
				users = Arrays.copyOf(users, (users.length) * 2);
			}
			users[lastAddedIndex++] = user;
			return NO_ERROR_MESSAGE;
		}
		else {
			return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
		}

	}

	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}


	@Override
	public User[] getUsers() {
		int nonNullUsers = 0;
		for(User user : users) {
			if(user != null) nonNullUsers++;
		}

		User[] realUsers = new User[nonNullUsers];

		int index = 0;
		for(User user : users){
			if(user != null) {
				realUsers[index++] = user;
			}
		}
		users = realUsers;
		return users;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		for(User user : users) {
			if(user != null && user.getEmail().equals(userEmail)) {
				return user;
			}
		}
		return null;
	}

	void clearServiceState() {
		lastAddedIndex = 0;
		users = new User[DEFAULT_USERS_CAPACITY];
	}
}
