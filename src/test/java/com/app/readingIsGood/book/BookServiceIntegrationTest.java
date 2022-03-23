package com.app.readingIsGood.book;

import com.app.readingIsGood.book.model.Book;
import com.app.readingIsGood.book.repository.BookRepository;
import com.app.readingIsGood.book.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Test(expected = OptimisticLockingFailureException.class)
    public void shouldDenyConcurrentUpdates() {
        Book book = new Book();
        book.setName("test");
        book.setAuthor("test");
        book.setStockCount(5);
        book = bookRepository.save(book);
        book.setAuthor("testyazar");

        Book sameBook = bookRepository
            .findById(book.getId())
            .orElseThrow();
        sameBook.setAuthor("testdegismis");
        bookRepository.save(sameBook);
        bookRepository.save(book);
    }

    @Test
    public void shouldSuccessfullyUpdateStock() {
        Book book = bookRepository.findFirstByName("test");
        Integer count = book.getStockCount();
        Book increasedBook = bookService.increaseStock(book.getId(), 1).getData();
        Assert.assertNotNull(increasedBook);
        Assert.assertNotEquals(count, increasedBook.getStockCount());
        Book decreasedBook = bookService.decreaseStock(book.getId(), 1).getData();
        Assert.assertNotNull(decreasedBook);
        Assert.assertEquals(count, decreasedBook.getStockCount());
    }
}
