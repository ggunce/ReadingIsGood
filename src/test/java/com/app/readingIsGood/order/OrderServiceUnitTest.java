package com.app.readingIsGood.order;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.repository.OrderRepository;
import com.app.readingIsGood.order.service.impl.OrderServiceImpl;
import com.app.readingIsGood.response.GenericResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceUnitTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Order order;

    @Test
    public void shouldHaveErrorWhenOrderNotExist() {
        when(orderRepository.findById(anyString())).thenReturn(Optional.empty());
        GenericResponse<Order> response = orderService.getOrderDetails("1111");
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getError());
        Assertions.assertNull(response.getData());
    }

    @Test
    public void shouldReturnOrderList() {
        when(orderRepository.findAllByOrderDateBetween(any(), any())).thenReturn(List.of(order));
        GenericResponse<List<Order>> response = orderService.getOrderList(LocalDate.now(), LocalDate.now());
        Assertions.assertNotNull(response.getData());
    }

}
