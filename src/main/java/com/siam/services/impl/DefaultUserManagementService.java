package com.siam.services.impl;

import com.siam.enteties.User;
import com.siam.enteties.impl.DefaultUser;
import com.siam.services.UserManagementService;
import com.siam.storage.impl.DefaultUserStoringService;


import java.util.List;

import java.util.Arrays;
public class DefaultUserManagementService implements UserManagementService {

	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
	private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR_MESSAGE = "";

	private static final int DEFAULT_USERS_CAPACITY = 10;

	private static DefaultUserManagementService instance;
	private static DefaultUserStoringService userStoringService;
	private int lastAddedIndex;


	static {
		userStoringService = DefaultUserStoringService.getInstance();
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
			userStoringService.saveUser(user);
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
	public List<User> getUsers() {
		List<User> users = userStoringService.loadUsers();
		return users;
	}

	@Override
	public User getUserByEmail(String userEmail) {
		List<User> users= userStoringService.loadUsers();
		for(User user : users) {
			if(user != null && user.getEmail().equals(userEmail)) {
				return user;
			}
		}
		return null;
	}

}
