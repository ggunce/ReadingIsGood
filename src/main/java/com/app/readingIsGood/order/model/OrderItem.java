package com.app.readingIsGood.order.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItem {

    @NotNull
    private String bookId;
    @NotNull
    @Min(value = 1)
    private Integer count;

}
