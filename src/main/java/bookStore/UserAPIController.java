package bookStore;

import bookStore.dto.SaleDTO;
import bookStore.entity.Book;
import bookStore.error.InvalidSaleException;
import bookStore.service.book.BookService;
import bookStore.service.book.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class UserAPIController {
    @Autowired
    BookService bookService;
    @Autowired
    SaleService saleService;

    @RequestMapping(value = "/sellBook", method = RequestMethod.POST)
    public ResponseEntity<Integer> sellBook(@RequestBody SaleDTO sale) throws InvalidSaleException{
        Integer total = saleService.sell(sale);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchByTitle", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> searchByTitle(String title) throws EntityNotFoundException {
        List<Book> books = bookService.getByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchByAuthor", method = RequestMethod.GET)
    public List<Book> searchByAuthor(String auth) {
        return bookService.getByAuthor(auth);
    }

    @RequestMapping(value = "/searchByGenre", method = RequestMethod.GET)
    public List<Book> searchByGenre(String genre) {
        return bookService.getByGenre(genre);
    }

}
