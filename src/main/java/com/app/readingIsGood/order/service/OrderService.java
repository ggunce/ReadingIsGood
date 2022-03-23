package com.app.readingIsGood.order.service;

import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.response.GenericResponse;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    GenericResponse<Order> create(Order order);

    Order saveOrder(Order order);

    GenericResponse<Order> getOrderDetails(String id);

    GenericResponse<List<Order>> getOrderList(LocalDate startDate, LocalDate endDate);

}
