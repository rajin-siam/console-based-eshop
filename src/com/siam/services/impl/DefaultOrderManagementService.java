package com.siam.services.impl;


import com.siam.enteties.Order;
import com.siam.enteties.impl.DefaultOrder;
import com.siam.services.OrderManagementService;
import com.siam.storage.OrderStoringService;
import com.siam.storage.impl.DefaultOrderStoringService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DefaultOrderManagementService implements OrderManagementService {
	private static DefaultOrderManagementService instance;
	private List<Order> orders;
	private static OrderStoringService orderStoringService;

	{
		orderStoringService = DefaultOrderStoringService.getInstance();
		orders = orderStoringService.loadOrders();
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
		orders.add(order);
		orderStoringService.saveOrders(orders);
		return;
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		int amountOfUserOrders = 0;
		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId) {
				amountOfUserOrders++;
			}
		}

		int index = 0;
		List<Order> userOrders = new ArrayList<>();
		for (Order order : orders) {
			if (order != null && order.getCustomerId() == userId) {
				userOrders.add(order);
			}
		}
		return userOrders;
	}




	@Override
	public List<Order> getOrders() {
		if (orders == null || orders.size() == 0) {
			orders = orderStoringService.loadOrders();
		}
		return this.orders;
	}



}
