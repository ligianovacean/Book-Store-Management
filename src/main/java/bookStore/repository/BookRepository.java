package bookStore.repository;

import bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String name);
    Book findByTitleAndAuthorId(String title, int id);
    List<Book> findByQuantity(Integer quantity);
    List<Book> findByAuthorId(Integer id);
    List<Book> findByGenre(String genre);
}