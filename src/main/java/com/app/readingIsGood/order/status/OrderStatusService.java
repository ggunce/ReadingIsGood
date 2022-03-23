package com.app.readingIsGood.order.status;

import com.app.readingIsGood.order.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {

    public Order process(IOrder order) {
        return order.process();
    }
}
