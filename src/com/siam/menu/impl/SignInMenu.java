package com.siam.menu.impl;

import com.siam.configs.ApplicationContext;
import com.siam.enteties.User;
import com.siam.menu.Menu;
import com.siam.services.UserManagementService;
import com.siam.services.impl.DefaultUserManagementService;

import java.util.Scanner;
public class SignInMenu implements Menu {

	private ApplicationContext context;
	private UserManagementService userManagementService;

	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);
		System.out.print("Please Enter Your Email :");
		String email = sc.next();
		System.out.print("Please Enter Your Password :");
		String password = sc.next();

		User user = userManagementService.getUserByEmail(email);
		if(user != null && user.getPassword().equals(password)) {
			context.setLoggedInUser(user);
			System.out.println("Glad to see you back " + user.getFirstName() + " "+ user.getLastName() );
		}
		else {
			System.out.println("Unfortunately, such login and password doesn't exist");
		}

		context.getMainMenu().start();

	}

	@Override
	public void printMenuHeader() {
		System.out.println("***Sing In***");
	}

}
