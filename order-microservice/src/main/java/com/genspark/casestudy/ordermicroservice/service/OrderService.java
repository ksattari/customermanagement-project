package com.genspark.casestudy.ordermicroservice.service;

import com.genspark.casestudy.ordermicroservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);
    void deleteOrder(Long orderId);
    List<Order> getOrders();
    Order getOrder(Long orderId);

}
