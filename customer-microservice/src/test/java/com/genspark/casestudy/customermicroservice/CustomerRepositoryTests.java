package com.genspark.casestudy.customermicroservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.genspark.casestudy.customermicroservice.model.Customer;
import com.genspark.casestudy.customermicroservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository repo;

    // test methods go below

    @Test
    public void testCreatecustomer() {
        Customer customer = new Customer();
        customer.setEmail("ravikumar@gmail.com");
        customer.setPassword("ravi2020");
        customer.setFirstName("Ravi");
        customer.setLastName("Kumar");

        Customer savedcustomer = repo.save(customer);

        Customer existcustomer = entityManager.find(Customer.class, savedcustomer.getCustomerId());

        assertThat(customer.getEmail()).isEqualTo(existcustomer.getEmail());

    }

}
