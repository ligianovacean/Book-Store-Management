package bookStore.service.book;

import bookStore.dto.BookDto;
import bookStore.entity.Book;

import java.util.List;

public interface BookService {
    Book findById(Integer id);

    List<Book> getAll();
    List<Book> getByTitle(String title);
    List<Book> getByAuthor(String author);
    List<Book> getByGenre(String genre);
    Book create(BookDto book);
    void delete(Integer id);
    Book update(BookDto book);
    Book get(String title, Integer authorId);
    List<Book> getOutOfStock();
}
