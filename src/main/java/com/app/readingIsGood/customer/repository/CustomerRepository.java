package com.app.readingIsGood.customer.repository;

import com.app.readingIsGood.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {

    boolean existsCustomerByEmail(String email);

    Customer findByName(String name);

}
