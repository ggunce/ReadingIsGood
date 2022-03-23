package com.app.readingIsGood.order.status.converter;

import com.app.readingIsGood.order.enumarated.OrderState;
import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.status.ApprovedOrder;
import com.app.readingIsGood.order.status.CancelledOrder;
import com.app.readingIsGood.order.status.WaitingOrder;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderConverter {

    private final WaitingOrder waitingOrder;
    private final ApprovedOrder approvedOrder;
    private final CancelledOrder cancelledOrder;

    public WaitingOrder convertWaiting(Order order) {
        waitingOrder.setOrderId(order.getOrderId());
        waitingOrder.setOrderDate(LocalDate.now());
        waitingOrder.setOrderStatus(OrderState.WAITING);
        waitingOrder.setOrderItemList(order.getOrderItemList());
        waitingOrder.setCustomerId(order.getCustomerId());
        return waitingOrder;
    }

    public ApprovedOrder convertApproved(Order order) {
        approvedOrder.setOrderId(order.getOrderId());
        approvedOrder.setOrderStatus(OrderState.APPROVED);
        approvedOrder.setOrderDate(order.getOrderDate());
        approvedOrder.setCustomerId(order.getCustomerId());
        approvedOrder.setOrderItemList(order.getOrderItemList());
        return approvedOrder;
    }

    public CancelledOrder convertCancelled(Order order) {
        cancelledOrder.setOrderId(order.getOrderId());
        cancelledOrder.setOrderStatus(OrderState.CANCELLED);
        cancelledOrder.setOrderDate(order.getOrderDate());
        cancelledOrder.setCustomerId(order.getCustomerId());
        cancelledOrder.setOrderItemList(order.getOrderItemList());
        return cancelledOrder;
    }

}
