package com.app.readingIsGood.statistics.model;

import java.time.Month;
import lombok.Data;

@Data
public class OrderStatistic {

    private Month month;
    private Long orderCount;
    private Long bookCount;

}
