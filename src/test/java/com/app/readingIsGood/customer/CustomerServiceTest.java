package com.app.readingIsGood.customer;

import com.app.readingIsGood.customer.model.Customer;
import com.app.readingIsGood.customer.service.CustomerService;
import com.app.readingIsGood.response.GenericResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

@SpringBootTest
public class CustomerServiceTest {

    private final String EXIST_EMAIL = "cpp@ggg";

    @Autowired
    private CustomerService customerService;

    @Test
    public void saveHasErrorWhenAlreadyExist() {
        Customer customer = new Customer();
        customer.setEmail(EXIST_EMAIL);
        GenericResponse<Customer> response = customerService.save(customer);
        Assert.assertNotNull(response.getError());
    }

}
