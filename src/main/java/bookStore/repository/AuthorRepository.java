package bookStore.repository;

import bookStore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AuthorRepository extends JpaRepository<Author, Integer> {
   List<Author> findByName(String name);
}
