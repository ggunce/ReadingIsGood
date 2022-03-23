package com.app.readingIsGood.order.repository;

import com.app.readingIsGood.order.model.Order;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {

    Page<Order> findAllByCustomerId(String customerId, Pageable pageable);

    List<Order> findAllByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    List<Order> findAllByOrderDateBetweenAndCustomerId(LocalDate startDate, LocalDate endDate, String customerId);


}
