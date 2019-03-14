package bookStore.integration;

import bookStore.Application;
import bookStore.dto.AuthorDTO;
import bookStore.entity.Author;
import bookStore.service.author.AuthorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
@SpringBootApplication
public class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @Test
    public void createAndDelete(){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.name = "Vasile Alecsandri";
        Author author = authorService.create(authorDTO);
        Assert.assertTrue(author.getName().equals("Vasile Alecsandri"));
        authorService.delete(author.getId());
        Assert.assertNull(authorService.findById(author.getId()));
    }

    @Test
    public void findAll(){
        Assert.assertTrue(authorService.getAll().size() > 0);
    }

    @Test
    public void findByName(){
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.name = "Vasile Alecsandri";
        Author author = authorService.create(authorDTO);
        Assert.assertNotNull(authorService.findByName("Vasile Alecsandri"));
        authorService.delete(author.getId());
    }
}
