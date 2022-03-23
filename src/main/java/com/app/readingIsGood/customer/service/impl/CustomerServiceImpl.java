package com.app.readingIsGood.customer.service.impl;

import com.app.readingIsGood.customer.model.Customer;
import com.app.readingIsGood.customer.model.GetCustomerOrdersRequest;
import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.repository.OrderRepository;
import com.app.readingIsGood.response.GenericResponse;
import com.app.readingIsGood.customer.repository.CustomerRepository;
import com.app.readingIsGood.customer.service.CustomerService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    public GenericResponse<Customer> save(Customer customer) {
        GenericResponse<Customer> response = new GenericResponse<>();
        try {
            if (!isCustomerExist(customer)) {
                customer.setPassword(passwordEncoder.encode(customer.getPassword()));
                response.setData(customerRepository.save(customer));
                return response;
            }
            response.setError("Customer already exist");
            logger.warn("Customer already exist, email:{}", customer.getEmail());
        } catch (Exception e) {
            response.setError(e.getMessage());
            logger.error("Customer could not be created, email:{}, error:{}", customer.getEmail(), e);
        }
        return response;
    }

    @Override
    public GenericResponse<Map<String, Object>> getOrders(GetCustomerOrdersRequest request) {
        GenericResponse<Map<String, Object>> response = new GenericResponse<>();
        try {
            Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
            Page<Order> orders = orderRepository.findAllByCustomerId(request.getCustomerId(), pageable);
            Map<String, Object> res = new HashMap<>();
            res.put("orders", orders.getContent());
            res.put("totalPages", orders.getTotalPages());
            res.put("totalItems", orders.getTotalElements());
            response.setData(res);
        } catch (Exception e) {
            response.setError(e.getMessage());
        }
        return response;
    }

    private boolean isCustomerExist(Customer customer) {
        return customerRepository.existsCustomerByEmail(customer.getEmail());
    }

}
