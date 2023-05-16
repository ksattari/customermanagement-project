package com.genspark.casestudy.adminmicroservice.model;

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
    private LocalDateTime orderDate;
    private String orderItemName;
    private Double orderItemPrice;
    private Integer qty;
    private String idDownloadURL;

}