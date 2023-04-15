package com.genspark.casestudy.ordermicroservice.controller;


import com.genspark.casestudy.ordermicroservice.entity.Attachment;
import com.genspark.casestudy.ordermicroservice.entity.Order;
import com.genspark.casestudy.ordermicroservice.service.AttachmentService;
import com.genspark.casestudy.ordermicroservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class OrderServiceController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/saveOrder")
    public Order addOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @GetMapping("/getOrder/{id}")
    public Order getOrder(@PathVariable String id){
        return orderService.getOrder(Long.parseLong(id));
    }

    @GetMapping("/getOrders")
    public List<Order> getOrders(){

        return orderService.getOrders();
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}
