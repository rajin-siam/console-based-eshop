package com.siam.menu.impl;

import com.siam.configs.ApplicationContext;
import com.siam.menu.Menu;

import java.util.Scanner;

public class SettingsMenu implements Menu {

	private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
			+ "2. Change Email";

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		Menu menuToNavigate;
		mainLoop: while (true) {
			printMenuHeader();
			if(context.getLoggedInUser() == null) {
				System.out.println("‘Please, log in or create new account to change your account settings");
				menuToNavigate = new MainMenu();
				break mainLoop;
			}
			System.out.println(SETTINGS);
			Scanner sc = new Scanner(System.in);
			String option = sc.next();
			if(option.equals("menu")) {
				menuToNavigate = new MainMenu();
				break mainLoop;
			}

			switch (Integer.parseInt(option)) {
				case 1:
					menuToNavigate =new ChangePasswordMenu();
					break mainLoop;
				case 2:
					menuToNavigate = new ChangeEmailMenu();
					break mainLoop;
				default:
					System.out.println("Only 1, 2 is allowed. Try one more time.");
			}
		}
		menuToNavigate.start();

	}

	@Override
	public void printMenuHeader() {
		System.out.println("****Settings Menu****");
	}

}
