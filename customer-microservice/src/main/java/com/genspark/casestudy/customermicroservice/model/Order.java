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

}
