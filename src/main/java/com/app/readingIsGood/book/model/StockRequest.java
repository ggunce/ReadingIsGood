package com.app.readingIsGood.book.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockRequest {

    @NotNull
    private String id;
    @Min(value=0)
    private Integer count;

}
