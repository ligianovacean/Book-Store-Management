package bookStore.service.author;

import bookStore.dto.AuthorDTO;
import bookStore.entity.Author;

import java.util.List;


public interface AuthorService {
    List<Author> getAll();
    List<Author> findByName(String name);
    Author findById(int authorId);
    Author create(AuthorDTO authorDTO);

    void delete(Integer id);
}
