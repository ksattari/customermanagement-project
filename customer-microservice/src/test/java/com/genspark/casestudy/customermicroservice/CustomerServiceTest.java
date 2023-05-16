package com.genspark.casestudy.customermicroservice;

import com.genspark.casestudy.customermicroservice.model.Order;
import com.genspark.casestudy.customermicroservice.service.CustomerService;
import com.genspark.casestudy.customermicroservice.service.CustomerServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    CustomerService customerService = new CustomerServiceImp("http://ORDER-SERVICE/saveOrder");


    @Test
    public void ReturnMockedObject() {

        LocalDateTime orderDate = LocalDateTime.now();
        Order order = new Order(752L,"David Sattari","ksattari@yahoo.com",
                "3213 Via Cajita, Carlsbad Ca 92008",
                orderDate,2,null);
        Order mockedOrder = new Order(111L,752L,"David Sattari",
                "ksattari@yahoo.com", "3213 Via Cajita, Carlsbad Ca 92008",
                orderDate,2,null);

        HttpEntity<Order> request = new HttpEntity<>(order);

        Mockito
                .when(restTemplate.postForObject("http://ORDER-SERVICE/saveOrder",
                        request,Order.class))
                .thenReturn(new ResponseEntity<Order>(mockedOrder,HttpStatus.OK).getBody());


        Order returnedOrder = customerService.sendOrder(order);
        Assertions.assertEquals(returnedOrder,mockedOrder);
    }
}
