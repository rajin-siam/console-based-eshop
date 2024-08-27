package com.siam.menu.impl;


import com.siam.configs.ApplicationContext;
import com.siam.enteties.Cart;
import com.siam.enteties.Product;
import com.siam.menu.Menu;
import com.siam.services.ProductManagementService;
import com.siam.services.impl.DefaultProductManagementService;

import java.util.List;
import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

	private static final String CHECKOUT_COMMAND = "checkout";
	private static final String MENU_COMMAND = "menu";
	private ApplicationContext context;
	private ProductManagementService productManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = DefaultProductManagementService.getInstance();
	}

	@Override
	public void start() {

		Scanner sc = new Scanner(System.in);
		Menu menuToNavigate = null;
		mainLoop:
		while (true) {
			printMenuHeader();
			System.out.println("Enter Product ID to add it to the cart or 'menu' if you want to navigate back to the menu");

			List<Product> products = productManagementService.getProducts();
			for (Product product : products) {
				System.out.println(product.toString());
			}

			System.out.println("Enter ID to add to Cart or 'menu' if you want to navigate back to the menu");

			String input = sc.nextLine();

			if (context.getLoggedInUser() == null) {
				menuToNavigate = new MainMenu();
				System.out.println("You are not logged in. Please, sign in or create new account");
				break;
			}

			if (input.equalsIgnoreCase(CHECKOUT_COMMAND)) {
				if(context.getSessionCart().isEmpty()) {
					System.out.println("Your cart is empty. Please, add product to cart first and then proceed with checkout");
				}
				else {
					menuToNavigate = new CheckoutMenu();
					break mainLoop;
				}

			} else if (input.equalsIgnoreCase(MENU_COMMAND)) {
				menuToNavigate = new MainMenu();
				break mainLoop;
			} else  {
				int id = Integer.parseInt(input);
				Product product = productManagementService.getProductById(id);
				if (product != null) {
					Cart cart = context.getSessionCart();
					cart.addProduct(product);
					System.out.println("Product " + product.getProductName() + " has been added to your cart. If you want to add a new product - enter the product id. If you want to proceed with checkout - enter word 'checkout' to console");
				} else {
					System.out.println("Product with ID " + id + " not found. Please, enter a valid product ID, 'checkout' to proceed with checkout, or 'menu' to navigate back to the main menu.");
				}
			}
		}
		menuToNavigate.start();
		sc.close();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("**** Product Catalog Menu ****");
	}

}
