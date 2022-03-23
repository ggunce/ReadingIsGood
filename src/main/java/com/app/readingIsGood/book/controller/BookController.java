package com.app.readingIsGood.book.controller;


import static com.app.readingIsGood.book.controller.BookEndpoint.DECREASE;
import static com.app.readingIsGood.book.controller.BookEndpoint.INCREASE;
import static com.app.readingIsGood.book.controller.BookEndpoint.SAVE;

import com.app.readingIsGood.book.model.Book;
import com.app.readingIsGood.book.model.StockRequest;
import com.app.readingIsGood.book.service.BookService;
import com.app.readingIsGood.response.GenericResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @PostMapping(value = SAVE)
    public GenericResponse<Book> save(@Valid @RequestBody Book book) {
        return service.save(book);
    }

    @PostMapping(value = INCREASE)
    public GenericResponse<Book> increaseStockCount(@Valid @RequestBody StockRequest request) {
        return service.increaseStock(request.getId(), request.getCount());
    }

    @PostMapping(value = DECREASE)
    public GenericResponse<Book> decreaseStockCount(@Valid @RequestBody StockRequest request) {
        return service.decreaseStock(request.getId(), request.getCount());
    }

}
