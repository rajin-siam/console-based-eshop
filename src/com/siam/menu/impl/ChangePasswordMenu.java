package com.siam.menu.impl;


import com.siam.configs.ApplicationContext;
import com.siam.menu.Menu;

import java.util.Scanner;

public class ChangePasswordMenu implements Menu {

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		System.out.print("Enter new password: ");
		Scanner scanner = new Scanner(System.in);
		String newPassword = scanner.nextLine();
		passwordChanger(newPassword);
		System.out.print("Your password has been successfully changed\n");
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** Change Password ***");
	}

	public void passwordChanger(String newPassword) {
		context.getLoggedInUser().setPassword(newPassword);
	}


}
