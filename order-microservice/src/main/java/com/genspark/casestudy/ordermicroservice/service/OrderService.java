package com.genspark.casestudy.ordermicroservice.service;

import com.genspark.casestudy.ordermicroservice.entity.Order;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    List<Order> getOrders();

    Order getOrder(Long orderId);

}
