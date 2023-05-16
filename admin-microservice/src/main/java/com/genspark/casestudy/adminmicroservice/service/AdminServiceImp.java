package com.genspark.casestudy.adminmicroservice.service;

import com.genspark.casestudy.adminmicroservice.Repository.UserRepository;
import com.genspark.casestudy.adminmicroservice.model.AdminUser;
import com.genspark.casestudy.adminmicroservice.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class AdminServiceImp implements AdminService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${getOrderbaseURL}")
    private String getOrderbaseURL;



    public void saveAdminUser(String username, String password) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);

        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        adminUser.setPassword(hashedPassword);

        userRepository.save(adminUser);
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = null;
        try {
            orders = restTemplate.getForObject( getOrderbaseURL + "/getOrders", List.class);
        }
        catch(RestClientException e){
            log.info(e.getMessage());
        }
        return orders;
    }

    @Override
    public Order getOrder(Long orderId) {
        Order order = null;
        try {
            order = restTemplate.getForObject(getOrderbaseURL + "/getOrder/" + orderId, Order.class);
        }
        catch(RestClientException e){
            log.info(e.getMessage());
        }
        return order;
    }
}