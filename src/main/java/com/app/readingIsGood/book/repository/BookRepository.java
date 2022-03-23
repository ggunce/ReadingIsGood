package com.app.readingIsGood.book.repository;

import com.app.readingIsGood.book.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String> {

    Book findFirstByName(String name);
}
