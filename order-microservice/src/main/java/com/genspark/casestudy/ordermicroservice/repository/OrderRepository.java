package com.genspark.casestudy.ordermicroservice.repository;

import com.genspark.casestudy.ordermicroservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order,Long> {
}
