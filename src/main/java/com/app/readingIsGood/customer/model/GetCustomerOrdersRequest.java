package com.app.readingIsGood.customer.model;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetCustomerOrdersRequest {

    @NotNull
    private String customerId;
    @NotNull
    private int page;
    @NotNull
    private int size;

}
