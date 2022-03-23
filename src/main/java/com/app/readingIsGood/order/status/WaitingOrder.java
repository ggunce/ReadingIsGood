package com.app.readingIsGood.order.status;

import com.app.readingIsGood.book.model.Book;
import com.app.readingIsGood.book.service.BookService;
import com.app.readingIsGood.order.enumarated.OrderState;
import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WaitingOrder extends Order implements IOrder {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    private final BookService bookService;

    @Override
    public Order process() {
        this.setOrderStatus(OrderState.APPROVED);
        this.getOrderItemList().forEach(this::stockCheck);
        return this;
    }

    private void stockCheck(OrderItem item) {
        Book book = bookService.findById(item.getBookId());
        if(book == null || book.getStockCount() < item.getCount()) {
            this.setOrderStatus(OrderState.CANCELLED);
            logger.warn("Order cancelled, id={}", this.getOrderId());
        }
    }
}
