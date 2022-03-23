package com.app.readingIsGood.order;

import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.service.OrderService;
import com.app.readingIsGood.response.GenericResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceIntegrationTest {

    private final String EXIST_ORDER_ID = "623b33cc9898591da34b9ecd";

    @Autowired
    private OrderService orderService;

    @Test
    public void shouldSuccessWhenOrderExist() {
        GenericResponse<Order> response = orderService.getOrderDetails(EXIST_ORDER_ID);
        Assertions.assertNotNull(response.getData());
    }

}
