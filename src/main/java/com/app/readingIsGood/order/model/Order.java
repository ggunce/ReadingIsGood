package com.app.readingIsGood.order.model;

import com.app.readingIsGood.order.enumarated.OrderState;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Order {

    @Id
    private String orderId;
    @NotNull
    private String customerId;
    @Valid
    private List<OrderItem> orderItemList;
    private LocalDate orderDate;
    @NotNull
    private OrderState orderStatus;

}
