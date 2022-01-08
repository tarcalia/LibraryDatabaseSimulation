package application.controller;

import application.domain.Author;
import application.domain.BookGenre;
import application.domain.BookRequest;
import application.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    private LibraryService libraryService;

    public HomeController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).map(b -> b.label).collect(Collectors.toList()));
        model.addAttribute("authors", libraryService.findAllAuthors().stream().map(Author::getName).collect(Collectors.toList()));
        model.addAttribute("books", libraryService.findAllBooks());
        return MAIN_PAGE;
    }

    @RequestMapping("/uploadBook")
    public String uploadBook(@RequestParam(value = "ISBNNumber") String isbn,
                             @RequestParam (value = "title") String title,
                             @RequestParam (value = "genre") String genre,
                             @RequestParam (value = "author") String author,
                             Model model) {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setISBNNumber(Integer.parseInt(isbn));
        bookRequest.setTitle(title);
        bookRequest.setAuthor(author);
        bookRequest.setBookGenre(genre);
        libraryService.saveBookEntity(bookRequest);
        model.addAttribute("message", "Book uploaded");
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).map(b -> b.label).collect(Collectors.toList()));
        model.addAttribute("authors", libraryService.findAllAuthors().stream().map(Author::getName).collect(Collectors.toList()));
        model.addAttribute("books", libraryService.findAllBooks());
        return MAIN_PAGE;
    }

    @RequestMapping("/search")
    public String search(@RequestParam(value = "searchedItem") String search, Model model) {
        model.addAttribute("message", "Search result:");
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).map(b -> b.label).collect(Collectors.toList()));
        model.addAttribute("authors", libraryService.findAllAuthors().stream().map(Author::getName).collect(Collectors.toList()));
        model.addAttribute("books", libraryService.search(search));
        return MAIN_PAGE;
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam(value = "isbn") String isbn, Model model) {
        libraryService.deleteBookEntity(Integer.valueOf(isbn));
        model.addAttribute("message", "Book deleted");
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).map(b -> b.label).collect(Collectors.toList()));
        model.addAttribute("authors", libraryService.findAllAuthors().stream().map(Author::getName).collect(Collectors.toList()));
        model.addAttribute("books", libraryService.findAllBooks());
        return MAIN_PAGE;
    }

    @RequestMapping("/addAuthor")
    public String addAuthor(@RequestParam(value = "name") String name, Model model) {
        libraryService.saveAuthorEntity(name);
        model.addAttribute("message",  name + " added");
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).map(b -> b.label).collect(Collectors.toList()));
        model.addAttribute("authors", libraryService.findAllAuthors().stream().map(Author::getName).collect(Collectors.toList()));
        model.addAttribute("books", libraryService.findAllBooks());
        return MAIN_PAGE;
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(value = "isbn") String isbn, Model model) {
        model.addAttribute("message", "Edit the following book:");
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).map(b -> b.label).collect(Collectors.toList()));
        model.addAttribute("authors", libraryService.findAllAuthors().stream().map(Author::getName).collect(Collectors.toList()));
        model.addAttribute("book", libraryService.findBookById(isbn));
        return "edit-book";
    }

    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam (value = "ISBNNumber") String isbn,
                             @RequestParam (value = "title") String title,
                             @RequestParam (value = "quantity") String quantity,
                             @RequestParam (value = "genre") String genre,
                             @RequestParam (value = "author") String author,
                             Model model) {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setISBNNumber(Integer.parseInt(isbn));
        bookRequest.setTitle(title);
        bookRequest.setAuthor(author);
        bookRequest.setBookGenre(genre);
        bookRequest.setQuantity(Integer.parseInt(quantity));
        libraryService.updateBook(bookRequest);
        model.addAttribute("message", "Book updated");
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).map(b -> b.label).collect(Collectors.toList()));
        model.addAttribute("authors", libraryService.findAllAuthors().stream().map(Author::getName).collect(Collectors.toList()));
        model.addAttribute("books", libraryService.findAllBooks());
        return MAIN_PAGE;
    }
}
