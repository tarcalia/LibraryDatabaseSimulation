package application.service.impl;

import application.domain.*;
import application.repository.AuthorRepository;
import application.repository.BookRepository;
import application.repository.BookOrderRepository;
import application.repository.CustomerRepository;
import application.service.LibraryService;
import org.springframework.stereotype.Service;
import application.controller.LibraryController;

import java.util.*;

/**
 * Service class for {@link LibraryController}.
 */
@Service
public class LibraryHandler implements LibraryService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BookOrderRepository bookOrderRepository;
    private CustomerRepository customerRepository;

    public LibraryHandler(BookRepository bookRepository,
                          AuthorRepository authorRepository,
                          BookOrderRepository bookOrderRepository,
                          CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookOrderRepository = bookOrderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBookEntity(BookRequest bookRequest) {
        if (bookRequest == null) {
            throw new IllegalArgumentException("Received book is null");
        }
        Author author = authorRepository.findByName(bookRequest.getAuthor());
        bookRepository.save(new Book(bookRequest.getISBNNumber(), bookRequest.getTitle(), 1, BookGenre.getBookGenreValue(bookRequest.getBookGenre()), author));
    }

    @Override
    public void deleteBookEntity(Integer isbn) {
        bookRepository.delete(bookRepository.findByISBNNumber(isbn));
    }

    @Override
    public void saveAuthorEntity(String name) {
        authorRepository.save(new Author(name));
    }

    @Override
    public Book findBookById(String search) {
        return bookRepository.findByISBNNumber(Integer.parseInt(search));
    }

    @Override
    public List<Book> search(String search) {
        Set<Book> result = new HashSet<>();
        try {
            result.add(bookRepository.findByISBNNumber(Integer.valueOf(search)));
        } catch (NumberFormatException ignored) {
        }
        result.addAll(bookRepository.findByTitleContaining(search));
        result.addAll(bookRepository.findByAuthorNameContaining(search));
        return new ArrayList<>(result);
    }

    @Override
    public void updateBook(BookRequest bookRequest) {
        Book updatedBook = bookRepository.findByISBNNumber(bookRequest.getISBNNumber());
        updatedBook.setTitle(bookRequest.getTitle());
        Author author = authorRepository.findByName(bookRequest.getAuthor());
        updatedBook.setAuthor(author);
        updatedBook.setBookGenre(BookGenre.getBookGenreValue(bookRequest.getBookGenre()));
        updatedBook.setQuantity(bookRequest.getQuantity());
        bookRepository.save(updatedBook);
    }

    @Override
    public void orderBook(Integer isbn, Integer customerId) {
        Customer orderingCustomer;
        try {
            orderingCustomer = customerRepository.getById(customerId);
            Book preOrderedBook = bookRepository.findByISBNNumber(isbn);
            preOrderedBook.setQuantity(preOrderedBook.getQuantity() - 1);
            bookRepository.save(preOrderedBook);
            bookOrderRepository.save(new BookOrder(preOrderedBook, orderingCustomer));
        } catch (Exception e) {
            throw new NoSuchElementException("No customer found with this id");
        }
    }

    @Override
    public List<BookOrder> findAllBookOrder() {
        return bookOrderRepository.findAll();
    }

    @Override
    public void returnBook(Integer orderId) {
        BookOrder orderBookToReturn = bookOrderRepository.findById(orderId).get();
        Book returningBook = bookRepository.findByISBNNumber(orderBookToReturn.getBook().getISBNNumber());
        returningBook.setQuantity(returningBook.getQuantity() + 1);
        bookRepository.save(returningBook);
        bookOrderRepository.delete(orderBookToReturn);
    }

}
