package com.genspark.casestudy.adminmicroservice.service;

import com.genspark.casestudy.adminmicroservice.model.Order;

import java.util.List;

public interface AdminService {

    void saveAdminUser(String username, String password);

    List<Order> getOrders();

    Order getOrder(Long orderId);
}
