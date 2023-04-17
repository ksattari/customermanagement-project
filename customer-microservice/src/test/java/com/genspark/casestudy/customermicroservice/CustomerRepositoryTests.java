package com.genspark.casestudy.customermicroservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.genspark.casestudy.customermicroservice.model.Customer;
import com.genspark.casestudy.customermicroservice.model.Dependent;
import com.genspark.casestudy.customermicroservice.repository.CustomerRepository;
import com.genspark.casestudy.customermicroservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
@Slf4j
public class CustomerRepositoryTests {


    @Autowired
    private CustomerService customerService;


    // test methods go below

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setEmail("Kashman@gmail.com");
        customer.setPassword("kash2023");
        customer.setFirstName("Kash");
        customer.setLastName("Sattar");

        Customer savedCustomer = customerService.saveCustomer(customer);

        Customer existCustomer = customerService.findCustomer(savedCustomer.getCustomerId());
        log.info(savedCustomer.toString());

        assertThat(savedCustomer).isEqualTo(existCustomer);

    }
    @Test
    public void testCreateRemoveDependent(){
        Customer customer = new Customer();
        customer.setEmail("Kashman@gmail.com");
        customer.setPassword("kash2023");
        customer.setFirstName("Kash");
        customer.setLastName("Sattar");
        customer.addDependent(new Dependent("Mickey","Mouse",11));
        customer.addDependent(new Dependent("Thomas","Mouse",8));

        //test add dependent
        Customer savedCustomer =  customerService.saveCustomer(customer);
        List<Dependent> savedDependents = customerService.saveCustomer(customer).getDependents();
        Customer existCustomer = customerService.findCustomer(savedCustomer.getCustomerId());
        List<Dependent> existDependents = existCustomer.getDependents();

        log.info(savedDependents.toString());

        assertThat(savedDependents).isEqualTo(existDependents);

        //test remove dependent
        customer = savedCustomer;
        customer.removeDependent(savedCustomer.getDependents().get(0).getDependentId());
        savedDependents = customerService.saveCustomer(customer).getDependents();
        existCustomer = customerService.findCustomer(customer.getCustomerId());
        existDependents = existCustomer.getDependents();

        log.info(savedDependents.toString());

        assertThat(savedDependents).isEqualTo(existDependents);


    }

}
