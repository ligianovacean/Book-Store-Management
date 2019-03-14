package bookStore.service.book;

import bookStore.service.author.AuthorService;
import bookStore.dto.BookDto;
import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private AuthorService authorService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, @Value("${bookService.maxBooks}") Integer maxBooks) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public Book findById(Integer id){
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        if (books.size() == 0) {
            throw new EntityNotFoundException("No books were found!");
        }
        return books;
    }

    @Override
    public List<Book> getByAuthor(String authorName){
        List<Author> authors = authorService.findByName(authorName);
        List<Book> books = new ArrayList<>();
        for (Author author : authors) {
            books.addAll(bookRepository.findByAuthorId(author.getId()));
        }
        return books;
    }

    @Override
    public List<Book> getByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    @Override
    public Book create(BookDto bookDto) {
        Author author = authorService.findById(bookDto.authorId);
        Book b = new Book(bookDto.genre, bookDto.title, bookDto.price, bookDto.quantity, author);
        return bookRepository.save(b);
    }

    @Override
    public void delete(Integer id) {
        bookRepository.delete(id);
    }

    @Override
    public Book update(BookDto book) {
        Book bookToUpdate = bookRepository.findByTitleAndAuthorId(book.title, book.authorId);
        bookToUpdate.setGenre(book.genre);
        bookToUpdate.setPrice(book.price);
        bookToUpdate.setQuantity(book.quantity);
        return bookRepository.save(bookToUpdate);
    }

    @Override
    public Book get(String title, Integer authorId) {
        return bookRepository.findByTitleAndAuthorId(title, authorId);
    }

    @Override
    public List<Book> getOutOfStock() {
        return bookRepository.findByQuantity(0);
    }

}
