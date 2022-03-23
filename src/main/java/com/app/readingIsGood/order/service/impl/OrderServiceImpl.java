package com.app.readingIsGood.order.service.impl;

import com.app.readingIsGood.order.enumarated.OrderState;
import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.repository.OrderRepository;
import com.app.readingIsGood.order.service.OrderService;
import com.app.readingIsGood.order.status.converter.OrderConverter;
import com.app.readingIsGood.response.GenericResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final String NOT_FOUND = "Order detail not found";
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderConverter converter;
    private final OrderRepository orderRepository;

    @Override
    public GenericResponse<Order> create(Order order) {
        GenericResponse<Order> response = new GenericResponse<>();
        try {
            Order newOrder = saveOrder(order);
            newOrder = converter.convertWaiting(newOrder).process();
            if (newOrder.getOrderStatus() == OrderState.APPROVED) {
                newOrder = converter.convertApproved(newOrder).process();
            }
            if (newOrder.getOrderStatus() == OrderState.CANCELLED) {
                newOrder = converter.convertCancelled(newOrder).process();
            }
            response.setData(newOrder);
        } catch(Exception e) {
            response.setError(e.getMessage());
            logger.error("Order could not created, order={}, error={}", order, e.getMessage());
        }
        return response;
    }

    @Override
    public Order saveOrder(Order order) {
        try {
            return orderRepository.save(order);
        } catch(Exception e) {
            logger.error("Order could not saved, order={}, error={}", order, e.getMessage());
            return null;
        }
    }

    @Override
    public GenericResponse<Order> getOrderDetails(String id) {
        Optional<Order> order = orderRepository.findById(id);
        GenericResponse<Order> response = new GenericResponse<>();
        if(order.isPresent()) {
            response.setData(order.get());
        } else {
            response.setError(NOT_FOUND);
        }
        return response;
    }

    @Override
    public GenericResponse<List<Order>> getOrderList(LocalDate startDate, LocalDate endDate) {
        GenericResponse<List<Order>> response = new GenericResponse<>();
        response.setData(orderRepository.findAllByOrderDateBetween(startDate, endDate));
        return response;
    }

}
