package com.app.readingIsGood.order.controller;

import static com.app.readingIsGood.order.controller.OrderEndpoint.CREATE;
import static com.app.readingIsGood.order.controller.OrderEndpoint.GET;
import static com.app.readingIsGood.order.controller.OrderEndpoint.LIST;

import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.service.OrderService;
import com.app.readingIsGood.response.GenericResponse;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping(value = CREATE)
    public GenericResponse<Order> create(@Valid @RequestBody Order order) {
        return service.create(order);
    }

    @GetMapping(value = GET)
    public GenericResponse<Order> getOrderDetails(String orderId) {
        return service.getOrderDetails(orderId);
    }

    @GetMapping(value = LIST)
    public GenericResponse<List<Order>> getOrderList(
        @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
        return service.getOrderList(startDate, endDate);
    }

}
