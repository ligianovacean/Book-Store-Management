package bookStore.integration;

import bookStore.Application;
import bookStore.dto.BookDto;
import bookStore.dto.SaleDTO;
import bookStore.entity.Book;
import bookStore.repository.BookRepository;
import bookStore.service.author.AuthorService;
import bookStore.service.book.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
@SpringBootApplication
public class BookServiceTest {

    @Autowired
    BookService bookService;

    private static final String TITLE = "Crima si pedeapsa";
    private static final String AUTHOR = "Gabriel Garcia Marquez";


    @Test
    public void findAll(){
        Assert.assertNotNull(bookService.getAll());
    }

    @Test
    public void createAndDelete(){
        BookDto bookDto = new BookDto();
        bookDto.authorId = 1;
        bookDto.title = TITLE;
        bookDto.price = 45;
        bookDto.quantity = 0;
        bookDto.genre = "Drama";
        Book book = bookService.create(bookDto);
        Assert.assertTrue(book.getId() > 0);
        bookService.delete(book.getId());
        Assert.assertNull(bookService.findById(book.getId()));
    }

    @Test
    public void findByTitle(){
        BookDto bookDto = new BookDto();
        bookDto.authorId = 1;
        bookDto.title = TITLE;
        bookDto.price = 45;
        bookDto.quantity = 0;
        bookDto.genre = "Drama";
        Book book = bookService.create(bookDto);
        Assert.assertTrue(bookService.getByTitle(TITLE).get(0).getId() > 0 );
        bookService.delete(book.getId());
    }

    @Test
    public void findByAuthor(){
        BookDto bookDto = new BookDto();
        bookDto.authorId = 1;
        bookDto.title = TITLE;
        bookDto.price = 45;
        bookDto.quantity = 0;
        bookDto.genre = "Drama";
        Book book = bookService.create(bookDto);
        Assert.assertTrue(bookService.getByAuthor(AUTHOR).get(0).getId() > 0 );
        bookService.delete(book.getId());
    }

}
