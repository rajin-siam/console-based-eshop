package com.siam.menu.impl;


import com.siam.configs.ApplicationContext;
import com.siam.enteties.Order;
import com.siam.enteties.impl.DefaultOrder;
import com.siam.menu.Menu;
import com.siam.services.OrderManagementService;
import com.siam.services.impl.DefaultOrderManagementService;

import java.util.Scanner;

public class CheckoutMenu implements Menu {

	private ApplicationContext context;
	private OrderManagementService orderManagementService;

	{
		context = ApplicationContext.getInstance();
		orderManagementService = DefaultOrderManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		System.out.println("‘Enter your credit card number without spaces and press enter if you confirm purchase");
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String creditCardNumber = scanner.next();
			if(createOrder(creditCardNumber)) {
				System.out.println("Thanks a lot for your purchase. Details about order delivery are sent to your email.’\n");
				break;
			}
			else {
				System.out.println("You entered invalid credit card number. Valid credit card should contain 16 digits. Please, try one more time");
			}
		}


		context.getMainMenu().start();



	}

	private boolean createOrder(String creditCardNumber) {
		Order order = new DefaultOrder();
		if (!order.isCreditCardNumberValid(creditCardNumber)) {
			return false;
		}

		order.setCreditCardNumber(creditCardNumber);
		order.setProducts(context.getSessionCart().getProducts());

		order.setCustomerId(context.getLoggedInUser().getId());
		orderManagementService.addOrder(order);
		return true;
	}

	@Override
	public void printMenuHeader() {
		System.out.println("**** Checkout Menu ****");
	}

}
