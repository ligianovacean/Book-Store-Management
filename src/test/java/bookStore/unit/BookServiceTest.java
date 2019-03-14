package bookStore.unit;

import bookStore.dto.BookDto;
import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.repository.BookRepository;
import bookStore.service.author.AuthorService;
import bookStore.service.book.BookService;
import bookStore.service.book.BookServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorService authorService;

    private static final Integer MAX_BOOKS = 10;
    private static final int AUTHOR_ID = 1;
    private static final String TITLE = "Crima si pedeapsa";

    @Before
    public void setup(){
        bookService = new BookServiceImpl(bookRepository, authorService, MAX_BOOKS);
        Author author = new Author("F. Dostoievski");
        author.setId(AUTHOR_ID);
        Book book = new Book("Drama", TITLE, 45, 0, author);
        when(authorService.findById(AUTHOR_ID)).thenReturn(author);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookRepository.findByQuantity(0)).thenReturn(Collections.singletonList(book));
        when(bookRepository.findByTitleAndAuthorId(TITLE, AUTHOR_ID)).thenReturn(book);
    }

    @Test
    public void create(){
        BookDto bookDto = new BookDto();
        bookDto.authorId = 1;
        bookDto.title = TITLE;
        bookDto.price = 45;
        bookDto.quantity = 0;
        bookDto.genre = "Drama";
        Assert.assertEquals(bookService.create(bookDto).getTitle(), TITLE);
    }

    @Test
    public void getByTitleAndAuthorId(){
        Assert.assertEquals(bookService.get(TITLE, AUTHOR_ID).getTitle(), TITLE);
        Assert.assertEquals(bookService.get(TITLE, AUTHOR_ID).getAuthor().getId(), AUTHOR_ID);
    }

    @Test
    public void getOutOfStock(){
        Assert.assertEquals(bookService.getOutOfStock().get(0).getTitle(), TITLE);
    }

}
