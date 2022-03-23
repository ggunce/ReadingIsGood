package com.app.readingIsGood.customer.controller;

import static com.app.readingIsGood.customer.controller.CustomerEndpoint.ORDERS;
import static com.app.readingIsGood.customer.controller.CustomerEndpoint.SAVE;

import com.app.readingIsGood.customer.model.Customer;
import com.app.readingIsGood.customer.model.GetCustomerOrdersRequest;
import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.response.GenericResponse;
import com.app.readingIsGood.customer.service.CustomerService;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping(value = SAVE)
    public GenericResponse<Customer> save(@Valid @RequestBody Customer customer) {
        return service.save(customer);
    }

    @GetMapping(value = ORDERS)
    public GenericResponse<Map<String, Object>> getOrders(@Valid GetCustomerOrdersRequest request) {
        return service.getOrders(request);
    }

}
