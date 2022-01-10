package application.controller;

import application.domain.Author;
import application.domain.BookGenre;
import application.domain.BookRequest;
import application.service.IntToStringService;
import application.service.AuthorService;
import application.service.BookService;
import application.service.OrderService;
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
public class LibraryController {
    private final String MAIN_PAGE = "index";
    private final String INFORMATION = "information";
    private IntToStringService intToStringService;
    private AuthorService authorService;
    private BookService bookService;
    private OrderService orderService;

    public LibraryController(IntToStringService intToStringService, AuthorService authorService,
                             BookService bookService, OrderService orderService) {
        this.intToStringService = intToStringService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @RequestMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return MAIN_PAGE;
    }

    @RequestMapping("/upload")
    public String upload(Model model) {
        getModelAttributes(model);
        return "upload";
    }

    @RequestMapping("/uploadBook")
    public String uploadBook(@RequestParam(value = "ISBNNumber") String isbn,
                             @RequestParam (value = "title") String title,
                             @RequestParam (value = "genre") String genre,
                             @RequestParam (value = "author") String author,
                             Model model) {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setISBNNumber(intToStringService.convertToInteger(isbn));
        bookRequest.setTitle(title);
        bookRequest.setAuthor(author);
        bookRequest.setBookGenre(genre);
        bookService.saveBookEntity(bookRequest);
        model.addAttribute("message", "Book uploaded");
        getModelAttributes(model);
        model.addAttribute("books", bookService.findAllBooks());
        return MAIN_PAGE;
    }

    @RequestMapping("/search")
    public String search(@RequestParam(value = "searchedItem") String search, Model model) {
        model.addAttribute("message", "Search result:");
        getModelAttributes(model);
        model.addAttribute("books", bookService.search(search));
        return MAIN_PAGE;
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestParam(value = "isbn") String isbn, Model model) {
        bookService.deleteBookEntity(intToStringService.convertToInteger(isbn));
        model.addAttribute("message", "Book deleted");
        return INFORMATION;
    }

    @RequestMapping("/addAuthor")
    public String addAuthor(@RequestParam(value = "name") String name, Model model) {
        authorService.saveAuthorEntity(name);
        model.addAttribute("message",  name + " added");
        return INFORMATION;
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(value = "isbn") String isbn, Model model) {
        model.addAttribute("message", "Edit the following book:");
        getModelAttributes(model);
        model.addAttribute("book", bookService.findBookById(intToStringService.convertToInteger(isbn)));
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
        bookRequest.setISBNNumber(intToStringService.convertToInteger(isbn));
        bookRequest.setTitle(title);
        bookRequest.setAuthor(author);
        bookRequest.setBookGenre(genre);
        bookRequest.setQuantity(intToStringService.convertToInteger(quantity));
        bookService.updateBook(bookRequest);
        model.addAttribute("message", "Book updated");
        getModelAttributes(model);
        model.addAttribute("books", bookService.findAllBooks());
        return MAIN_PAGE;
    }

    @RequestMapping("/returnBook")
    public String returnBook(Model model) {
        model.addAttribute("message", "Books to return");
        model.addAttribute("bookOrders", orderService.findAllBookOrder());
        return "return-book";
    }

    @RequestMapping("return")
    public String returned(@RequestParam(value = "isbn") String isbn, Model model) {
        orderService.returnBook(intToStringService.convertToInteger(isbn));
        model.addAttribute("message", "Book returned");
        return INFORMATION;
    }

    private Model getModelAttributes(Model model) {
        model.addAttribute("genres", Arrays.stream(BookGenre.values()).map(b -> b.label).collect(Collectors.toList()));
        model.addAttribute("authors", authorService.findAllAuthors().stream().map(Author::getName).collect(Collectors.toList()));
        return model;
    }
}
