package com.app.readingIsGood.book;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.app.readingIsGood.book.model.Book;
import com.app.readingIsGood.book.repository.BookRepository;
import com.app.readingIsGood.book.service.impl.BookServiceImpl;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceUnitTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private Book book;

    @Mock
    private BookRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindBook(){
        when(repository.findById(anyString())).thenReturn(Optional.of(book));
        Book response = bookService.findById("1");
        Assert.assertNotNull(response);
    }

}
