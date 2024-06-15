package com.siam.services.impl;


import com.siam.enteties.Order;
import com.siam.enteties.impl.DefaultOrder;
import com.siam.services.OrderManagementService;
import java.util.Arrays;


public class DefaultOrderManagementService implements OrderManagementService {

	private static final int DEFAULT_ORDER_CAPACITY = 10;

	private static DefaultOrderManagementService instance;
	private int lastAddedIndex;
	private Order[] orders;


	{
		orders = new DefaultOrder[DEFAULT_ORDER_CAPACITY];
	}


	public static OrderManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultOrderManagementService();
		}
		return instance;
	}

	@Override
	public void addOrder(Order order) {
		if(order == null) {
			return;
		}
		if(orders.length <= lastAddedIndex) {
			orders = Arrays.copyOf(orders, (orders.length) * 2);
		}
		orders[lastAddedIndex++] = order;
	}

	@Override
	public Order[] getOrdersByUserId(int userId) {
		int amountOfUserOrders = 0;
		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId) {
				amountOfUserOrders++;
			}
		}

		int index = 0;
		Order[] userOrders = new DefaultOrder[amountOfUserOrders];
		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId) {
				userOrders[index++] = order;
			}
		}
		return userOrders;
	}




	@Override
	public Order[] getOrders() {
		int numberOfNonNullOrders = 0;
		for (Order order : orders) {
			if(order != null) {
				numberOfNonNullOrders++;
			}
		}
		Order [] nonNullOrders = new DefaultOrder[numberOfNonNullOrders];
		int index = 0;
		for (Order order : orders) {
			if(order != null) {
				nonNullOrders[index++] = order;
			}
		}
		return nonNullOrders;
	}




	void clearServiceState() {
		lastAddedIndex = 0;
		orders = new Order[DEFAULT_ORDER_CAPACITY];
	}

}
