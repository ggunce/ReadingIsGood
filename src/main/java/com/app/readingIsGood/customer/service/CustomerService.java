package com.app.readingIsGood.customer.service;

import com.app.readingIsGood.customer.model.Customer;
import com.app.readingIsGood.customer.model.GetCustomerOrdersRequest;
import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.response.GenericResponse;
import java.util.Map;
import org.springframework.data.domain.Page;

public interface CustomerService {

    GenericResponse<Customer> save(Customer customer);

    GenericResponse<Map<String, Object>> getOrders(GetCustomerOrdersRequest request);

}
