package com.service;

import com.dto.order.Order;

import java.util.List;

public interface OrderService {
    List<Order> allOrders();
    void add(Order order);
    void delete(Order order);
    void edit(Order order);
    Order getById(int id);
}
