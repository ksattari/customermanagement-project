package com.genspark.casestudy.customermicroservice.service;

import com.genspark.casestudy.customermicroservice.model.Attachment;
import com.genspark.casestudy.customermicroservice.model.Customer;
import com.genspark.casestudy.customermicroservice.model.Order;
import com.genspark.casestudy.customermicroservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImp implements CustomerService{

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${saveOrderURL}")
    private String saveOrderURL;

    @Override
    public Customer saveCustomer(Customer customer) {return repo.save(customer);}

    @Override
    public Customer findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Customer findCustomer(Long id) {
        Optional<Customer> op =  repo.findById(id);
        if(op.isPresent())
            return op.get();

        return null;

    }


    public Attachment createAttachment(MultipartFile file) throws Exception{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            Attachment attachment
                    = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes());
            return attachment;

        } catch (Exception e) {
            throw new Exception("Could not create Attachment: " + fileName);
        }
    }

    // **********    THIS IS WHERE YOU CALL THE ORDER_MICROSERVICE SAVE Order API **********  //
    @Override
    public Order sendOrder(Order order) {
        HttpEntity<Order> request = new HttpEntity<>(order);
        Order confirmationOrder = null;
        try {
            log.info(saveOrderURL);
            confirmationOrder = restTemplate.postForObject(saveOrderURL, request, Order.class);
        }
        catch(RestClientException e){
            log.info(e.getMessage());
        }
        return confirmationOrder;
    }
}
