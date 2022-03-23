package com.app.readingIsGood.statistics.controller;

import com.app.readingIsGood.statistics.model.OrderStatistic;
import com.app.readingIsGood.statistics.service.StatisticsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService service;

    @GetMapping(value = "statistics")
    public List<OrderStatistic> getOrderDetails(String customerId) {
        return service.getOrderStatistics(customerId);
    }
}
