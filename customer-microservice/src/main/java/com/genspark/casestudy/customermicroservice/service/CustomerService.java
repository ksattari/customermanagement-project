package com.genspark.casestudy.customermicroservice.service;

import com.genspark.casestudy.customermicroservice.model.Attachment;
import com.genspark.casestudy.customermicroservice.model.Customer;
import com.genspark.casestudy.customermicroservice.model.Order;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    Customer findByEmail(String email);
    Attachment createAttachment(MultipartFile file) throws Exception;

    Order sendOrder(Order order);
}
