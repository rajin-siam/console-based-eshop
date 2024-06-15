package com.siam.menu.impl;

import com.siam.configs.ApplicationContext;
import com.siam.menu.Menu;

import java.util.Scanner;

public class ChangeEmailMenu implements Menu {

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		System.out.print("Enter email password: ");
		Scanner scanner = new Scanner(System.in);
		String newEmail = scanner.nextLine();
		emailChanger(newEmail);
		System.out.print("Your Email has been successfully changed\n");
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** Change Password ***");
	}
	public void emailChanger(String newEmail) {
		context.getLoggedInUser().setEmail(newEmail);
	}

}



