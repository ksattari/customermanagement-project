package com.genspark.casestudy.customermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long orderId;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private LocalDateTime orderDate;   // for simplicity made it a preformatted String
    private final String orderItemName = "MedBed 7000";
    private final Double orderItemPrice = 19999.99;
    private Integer qty;
    private Attachment attachment;

    public Order(Long customerId, String customerName, String customerEmail,
                 String customerAddress, LocalDateTime orderDate, Integer qty, Attachment attachment) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.qty = qty;
        this.attachment = attachment;
    }
}
