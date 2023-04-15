package com.genspark.casestudy.ordermicroservice.utility;

import com.genspark.casestudy.ordermicroservice.entity.Attachment;
import com.genspark.casestudy.ordermicroservice.entity.Order;
import com.genspark.kafka.model.EmailConfirmation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Khashayar Sattari
 *
 * provides utility functions for this project
 *
 */
public class Utility {

    public static Order changeOrderAttachmentToURL(Order order){

        Attachment attachment = order.getAttachment();
        String downloadURl = "";

        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();
        order.setIdDownloadURL(downloadURl);
        order.setAttachment(null);  //set attachment to null for display in postman or as JSON
        return order;

    }

    public static EmailConfirmation buildEmailConfirmation(Order order){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String dateTimeString = order.getOrderDate()
                                    .format(formatter);
        Double total = order.getOrderItemPrice() * order.getQty();
        return new EmailConfirmation(order.getOrderId().toString(),
                                    dateTimeString,order.getCustomerName(),
                                    order.getCustomerEmail(),
                                    order.getCustomerAddress(),
                                    order.getOrderItemName(),
                                    order.getQty().toString(),
                                    order.getOrderItemPrice().toString(),
                                    total.toString());
    }
}
