package com.siam.menu.impl;


import com.siam.configs.ApplicationContext;
import com.siam.menu.Menu;

public class SignOutMenu implements Menu {

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		context.setLoggedInUser(null);
		System.out.println("Have a nice day! Look forward to welcoming back!’");
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*** Sign Out ***");

	}

}
