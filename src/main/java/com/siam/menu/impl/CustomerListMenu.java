package com.siam.menu.impl;

import com.siam.configs.ApplicationContext;

import com.siam.enteties.User;
import com.siam.menu.Menu;
import com.siam.services.UserManagementService;
import com.siam.services.impl.DefaultUserManagementService;

import java.util.List;

public class CustomerListMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		List<User> users = userManagementService.getUsers();
		for (User user : users) {
			System.out.println(user);
		}
		context.getMainMenu().start();;
	}

	@Override
	public void printMenuHeader() {
		System.out.println("****Customer List****");
	}

}
