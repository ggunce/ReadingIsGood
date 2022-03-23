package com.app.readingIsGood.order.status;

import com.app.readingIsGood.book.model.Book;
import com.app.readingIsGood.book.service.BookService;
import com.app.readingIsGood.order.enumarated.OrderState;
import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.model.OrderItem;
import com.app.readingIsGood.order.repository.OrderRepository;
import com.app.readingIsGood.response.GenericResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApprovedOrder extends Order implements IOrder {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    private final BookService bookService;
    private final OrderRepository orderRepository;

    @Override
    public Order process() {
        GenericResponse<List<Book>> response = updateStock(this.getOrderItemList());
        if(!response.hasError()) {
            this.setOrderStatus(OrderState.APPROVED);
            orderRepository.save(map(this));
            bookService.saveAll(response.getData());
            logger.info("Order successfully created, id={}", this.getOrderId());
        } else {
            this.setOrderStatus(OrderState.CANCELLED);
        }
        return this;
    }

    private GenericResponse<List<Book>> updateStock(List<OrderItem> itemList) {
        GenericResponse<List<Book>> response = new GenericResponse<>();
        List<Book> bookList = new ArrayList<>();
        for(OrderItem item : itemList) {
            GenericResponse<Book> book = bookService.decreaseStock(item.getBookId(), item.getCount());
            if(book.hasError()) {
                response.setError("Stock Error");
            } else {
                bookList.add(book.getData());
            }
        }
        response.setData(bookList);
        return response;
    }

    private Order map(ApprovedOrder source) {
        Order order = new Order();
        order.setOrderId(source.getOrderId());
        order.setOrderDate(source.getOrderDate());
        order.setOrderStatus(source.getOrderStatus());
        order.setOrderItemList(source.getOrderItemList());
        order.setCustomerId(source.getCustomerId());
        return order;
    }

}
