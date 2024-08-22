package com.siam.storage;

import com.siam.enteties.Order;

import java.util.List;

public interface OrderStoringService {
    void saveOrders(List<Order> orders);
    List<Order> loadOrders();

}
