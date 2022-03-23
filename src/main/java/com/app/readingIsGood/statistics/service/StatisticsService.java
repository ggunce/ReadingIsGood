package com.app.readingIsGood.statistics.service;

import com.app.readingIsGood.order.model.Order;
import com.app.readingIsGood.order.model.OrderItem;
import com.app.readingIsGood.order.repository.OrderRepository;
import com.app.readingIsGood.statistics.model.OrderStatistic;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StatisticsService {

    private final OrderRepository orderRepository;

    public List<OrderStatistic> getOrderStatistics(String customerId) {
        List<OrderStatistic> statistics = new ArrayList<>();
        for(int i=1; i<=12; i++) {
            Long count = 0L;
            OrderStatistic statistic = new OrderStatistic();

            LocalDate end = LocalDate.of(LocalDate.now().getYear(), i, 1)
                .with(TemporalAdjusters.lastDayOfMonth());
            LocalDate start = LocalDate.of(LocalDate.now().getYear(), i, 1)
                .with(TemporalAdjusters.firstDayOfMonth());

            List<Order> monthlyOrder = orderRepository.findAllByOrderDateBetweenAndCustomerId(start, end, customerId);

            for(Order order : monthlyOrder) {
                count += getBookCountFromOrder(order);
            }
            statistic.setOrderCount(monthlyOrder.stream().count());
            statistic.setMonth(Month.from(start));
            statistic.setBookCount(count);
            statistics.add(statistic);
        }
        return statistics;
    }

    private Long getBookCountFromOrder(Order order) {
        Long count = 0L;
        for(OrderItem item : order.getOrderItemList()) {
            count += item.getCount();
        }
        return count;
    }
}
