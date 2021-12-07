package application.controller;

import application.domain.Author;
import application.domain.Book;
import application.domain.BookGenre;
import application.repository.AuthorRepository;
import application.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Main controller for Spring Boot application
 */
@Controller
public class HomeController {
    private final String MAIN_PAGE = "index";
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public HomeController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).collect(Collectors.toList()));
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return MAIN_PAGE;
    }

    @RequestMapping("/uploadBook")
    public String uploadBook(@RequestParam(value = "isbn") String isbn,
                             @RequestParam (value = "title") String title,
                             @RequestParam (value = "genre") String genre,
                             @RequestParam (value = "author") String author,
                             Model model) {
        Book tempBook = new Book(Integer.parseInt(isbn), title, BookGenre.valueOf(genre), authorRepository.findByName(author));
        System.out.println(tempBook);
        bookRepository.save(tempBook);
        model.addAttribute("message", "Book uploaded");
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).collect(Collectors.toList()));
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return MAIN_PAGE;
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam(value = "isbn") String isbn) {
        bookRepository.delete(bookRepository.findBook(isbn));
        return MAIN_PAGE;
    }

    @RequestMapping("/addAuthor")
    public String addAuthor(@RequestParam(value = "name") String name, Model model) {
        authorRepository.save(new Author(name));
        model.addAttribute("message",  name + " added");
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).collect(Collectors.toList()));
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        return MAIN_PAGE;
    }

}
