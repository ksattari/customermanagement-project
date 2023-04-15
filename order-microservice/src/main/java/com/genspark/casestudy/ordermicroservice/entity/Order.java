package com.genspark.casestudy.ordermicroservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "tbl_order")
@Data@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @OneToOne (cascade = CascadeType.ALL)
    private Attachment attachment;

}
