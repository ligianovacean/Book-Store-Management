package bookStore;

import bookStore.dto.BookDto;
import bookStore.dto.ErrorResponse;
import bookStore.dto.UserDTO;
import bookStore.entity.Book;
import bookStore.entity.User;
import bookStore.error.ErrorCollector;
import bookStore.error.ValidationException;
import bookStore.service.book.BookService;
import bookStore.service.book.GoogleSearchService;
import bookStore.service.report.ReportService;
import bookStore.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
public class AdminAPIController {
    @Autowired
    BookService bookService;
    @Autowired
    ReportService reportService;
    @Autowired
    GoogleSearchService googleSearchService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/createUsers", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO user, BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            List<String> err = ErrorCollector.constructErrors(result);
            throw new ValidationException(errorListToString(err));
        }
        User createdUser = userService.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUsers", method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserDTO user, BindingResult result) throws ValidationException, EntityNotFoundException {
        if (result.hasErrors()) {
            List<String> err = ErrorCollector.constructErrors(result);
            throw new ValidationException(errorListToString(err));
        }
        User updatedUser = userService.update(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteUsers", method = RequestMethod.POST)
    public ResponseEntity deleteUser(@RequestBody String username) throws EntityNotFoundException{
           userService.delete(username.split("=")[1]);
           return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteBooks", method = RequestMethod.POST)
    public void deleteBook(@RequestBody Integer id) {
        bookService.delete(id);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @RequestMapping(value = "/createBooks", method = RequestMethod.POST)
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookDto book, BindingResult result) throws ValidationException{
        if (result.hasErrors()) {
            List<String> err = ErrorCollector.constructErrors(result);
            throw new ValidationException(errorListToString(err));
        }
        Book createdBook = bookService.create(book);
        return new ResponseEntity<>(createdBook, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateBooks", method = RequestMethod.POST)
    public ResponseEntity<Book> updateBook(@RequestBody @Valid BookDto book, BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            List<String> err = ErrorCollector.constructErrors(result);
            throw new ValidationException(errorListToString(err));
        }
        Book updatedBook = bookService.update(book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @RequestMapping(value = "/generateReport", method = RequestMethod.POST)
    public ResponseEntity generateReport(@RequestBody String reportType) throws IOException{
        reportService.generateReport(reportType);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/googleSearch", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> googleSearch(String searchBy) throws IOException, GeneralSecurityException{
        List<Book> books = googleSearchService.search(searchBy);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    private String errorListToString(List<String> errors) {
        String error = "";
        for (String err : errors) {
            error = error + err + "\n";
        }
        return error;
    }

}
