package com.genspark.casestudy.adminmicroservice.controller;

import com.genspark.casestudy.adminmicroservice.model.Order;
import com.genspark.casestudy.adminmicroservice.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class MenuItemController {

    @Autowired
    private AdminService adminService;

     @GetMapping("/getOrders")
     public List<Order> getOrders(){
         return adminService.getOrders();
     }
}
