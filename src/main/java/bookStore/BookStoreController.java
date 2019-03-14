package bookStore;

import bookStore.service.author.AuthorService;
import bookStore.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookStoreController {
    @Autowired
    AuthorService authorService;
    @Autowired
    BookService bookService;


    @RequestMapping("/admin")
    String admin(Model model) {
        return "admin";
    }

    @RequestMapping("/admin-book")
    String adminBookPage(Model model) {
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("books", bookService.getAll());
        return "book-operations";
    }

    @RequestMapping("/admin-user")
    String adminUserPage(Model model) {
        model.addAttribute("authors", authorService.getAll());
        return "user-operations";
    }

    @RequestMapping("user")
    String user(Model model){
        model.addAttribute("authors", authorService.getAll());
        model.addAttribute("books", bookService.getAll());
        return "user";
    }
}
