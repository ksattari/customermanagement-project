package com.genspark.casestudy.ordermicroservice.service;

import com.genspark.casestudy.ordermicroservice.entity.Order;
import com.genspark.casestudy.ordermicroservice.repository.AttachmentRepository;
import com.genspark.casestudy.ordermicroservice.repository.OrderRepository;
import com.genspark.casestudy.ordermicroservice.utility.Utility;
import com.genspark.kafka.model.EmailConfirmation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    KafkaTemplate<String,EmailConfirmation> kafkaTemplate;

    @Value("${topic.name}")
    String topicName;

    @Override
    public Order saveOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        //send order to Kafka for confirmation email
        order = repo.save(order);
        order.setAttachment(null);
        EmailConfirmation confirm = Utility.buildEmailConfirmation(order);
        kafkaTemplate.send(topicName,confirm);
        log.info("Order was sent to Kafka  -> " + order);
        return order;

    }

    @Override
    public void deleteOrder(Long orderId) {
        repo.deleteById(orderId);
    }

    @Override
    public List<Order> getOrders() {

        List<Order> orders = repo.findAll();
        for(Order o : orders){
            o = Utility.changeOrderAttachmentToURL(o);
        }
        return orders;
    }

    @Override
    public Order getOrder(Long orderId) {

        Optional<Order> op = repo.findById(orderId);
        Order order = null;
        if(op.isPresent()) {
            order = op.get();
            order = Utility.changeOrderAttachmentToURL(order);
        }
        return order;
    }
}
