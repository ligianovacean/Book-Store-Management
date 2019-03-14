package bookStore;

import bookStore.dto.AuthorDTO;
import bookStore.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("author", new AuthorDTO());
        return "author-form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid AuthorDTO authorDTO) {
        authorService.create(authorDTO);
        return "redirect:create?success";
    }
}
