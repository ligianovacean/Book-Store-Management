package bookStore.service.author;

import bookStore.dto.AuthorDTO;
import bookStore.entity.Author;
import bookStore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    @Override
    public Author findById(int authorId) {
        return authorRepository.findOne(authorId);
    }

    @Override
    public Author create(AuthorDTO authorDTO) {
        return authorRepository.save(new Author(authorDTO.name));
    }

    @Override
    public void delete(Integer id) {
        authorRepository.delete(id);
    }
}
