package com.app.readingIsGood.order.status;

import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelledOrder extends Order implements IOrder {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    private final OrderRepository orderRepository;

    @Override
    public Order process() {
        orderRepository.save(map(this));
        logger.warn("Order cancelled, id={}", this.getOrderId());
        return this;
    }

    private Order map(CancelledOrder source) {
        Order order = new Order();
        order.setOrderId(source.getOrderId());
        order.setOrderDate(source.getOrderDate());
        order.setOrderStatus(source.getOrderStatus());
        order.setOrderItemList(source.getOrderItemList());
        order.setCustomerId(source.getCustomerId());
        return order;
    }
}
