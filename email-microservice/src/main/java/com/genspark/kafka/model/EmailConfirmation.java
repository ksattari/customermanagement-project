package com.genspark.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailConfirmation {

    private String orderConfirmation;
    private String orderDate;
    private String customerName;
    private String customerEmail;
    private String customerAddress;
    private String orderItemName;
    private String orderItemQty;
    private String orderItemPrice;
    private String orderTotal;

}