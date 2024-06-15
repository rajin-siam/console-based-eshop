package com.siam.menu.impl;

import com.siam.configs.ApplicationContext;
import com.siam.enteties.Order;
import com.siam.menu.Menu;
import com.siam.services.OrderManagementService;
import com.siam.services.impl.DefaultOrderManagementService;

import java.util.List;

public class MyOrdersMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		if(context.getLoggedInUser() == null) {
			System.out.println("Please, log in or create new account to see list of your orders");
			context.getMainMenu().start();
			return;
		}

		printUserOrders();
		context.getMainMenu().start();
	}
	private void printUserOrders () {
		int userID = context.getLoggedInUser().getId();
		Order[] LoggedInUserOrders = orderManagementService.getOrdersByUserId(userID);
		if(LoggedInUserOrders.length == 0) {
			System.out.println("Unfortunately, you don’t have any orders yet. Navigate back to main menu to place a new order");
			return;
		}
		if(LoggedInUserOrders == null || LoggedInUserOrders.length == 0) {
			System.out.println("Unfortunately, you don't have any orders yet. "
					+ "Navigate back to main menu to place a new order");
			return;
		}
		for (Order order : LoggedInUserOrders) {
			System.out.println(order);
		}

	}
	@Override
	public void printMenuHeader() {
		System.out.println("****My Orders Menu****");
	}

}
