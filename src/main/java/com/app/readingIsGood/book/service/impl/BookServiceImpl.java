package com.app.readingIsGood.book.service.impl;

import com.app.readingIsGood.book.model.Book;
import com.app.readingIsGood.book.repository.BookRepository;
import com.app.readingIsGood.book.service.BookService;
import com.app.readingIsGood.response.GenericResponse;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository repository;

    @Override
    public GenericResponse<Book> save(Book book) {
        GenericResponse<Book> response = new GenericResponse<>();
        try {
            response.setData(repository.save(book));
        } catch(Exception e) {
            response.setError(e.getMessage());
            logger.error("Book could not be created, name:{}, error:{}", book.getName(), e);
        }
        return response;
    }

    @Override
    public GenericResponse<Book> increaseStock(String id, Integer count) {
        GenericResponse<Book> response = new GenericResponse<>();
        try {
            Optional<Book> data = repository.findById(id);
            if(data.isPresent()) {
                Book book = data.get();
                book.setStockCount(book.getStockCount() + count);
                response.setData(repository.save(book));
                logger.info("Book stock updated successfully id:{}", id);
            }
        } catch(Exception e) {
            response.setError(e.getMessage());
            logger.error("Book stock could not be increased, id:{}, error:{}", id, e);
        }
        return response;
    }

    @Override
    public GenericResponse<Book> decreaseStock(String id, Integer count) {
        GenericResponse<Book> response = new GenericResponse<>();
        try {
            Optional<Book> data = repository.findById(id);
            if(data.isPresent() && data.get().getStockCount() >= count) {
                Book book = data.get();
                book.setStockCount(book.getStockCount() - count);
                response.setData(book);
                logger.info("Book stock updated successfully id:{}", id);
            } else {
                response.setError("Book stock could not be increased");
            }
        } catch(Exception e) {
            response.setError(e.getMessage());
            logger.error("Book stock could not be increased, id:{}, error:{}", id, e);
        }
        return response;
    }

    @Override
    public Book findById(String id) {
        Optional<Book> data = repository.findById(id);
        return data.orElse(null);
    }

    @Override
    public GenericResponse<List<Book>> saveAll(List<Book> bookList) {
        GenericResponse<List<Book>> response = new GenericResponse<>();
        try {
            response.setData(repository.saveAll(bookList));
        } catch(Exception e) {
            response.setError(e.getMessage());
        }
        return response;
    }
}
