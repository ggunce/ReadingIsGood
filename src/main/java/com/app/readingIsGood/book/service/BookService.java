package com.app.readingIsGood.book.service;

import com.app.readingIsGood.book.model.Book;
import com.app.readingIsGood.response.GenericResponse;
import java.util.List;

public interface BookService {

    GenericResponse<Book> save(Book book);

    GenericResponse<Book> increaseStock(String id, Integer count);

    GenericResponse<Book> decreaseStock(String id, Integer count);

    Book findById(String id);

    GenericResponse<List<Book>> saveAll(List<Book> book);
}
