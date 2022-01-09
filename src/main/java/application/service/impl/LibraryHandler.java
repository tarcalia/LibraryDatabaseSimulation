package application.service.impl;

import application.domain.Author;
import application.domain.Book;
import application.domain.BookRequest;
import application.domain.BookGenre;
import application.domain.BookOrder;
import application.domain.Customer;
import application.repository.AuthorRepository;
import application.repository.BookRepository;
import application.repository.BookOrderRepository;
import application.repository.CustomerRepository;
import application.service.IntToStringService;
import application.service.LibraryService;
import org.springframework.stereotype.Service;
import application.controller.LibraryController;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Service class for {@link LibraryController}.
 */
@Service
public class LibraryHandler implements LibraryService {
    private final Integer BOOK_QUANTITY = 1;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BookOrderRepository bookOrderRepository;
    private CustomerRepository customerRepository;
    private IntToStringService intToStringService;

    public LibraryHandler(BookRepository bookRepository,
                          AuthorRepository authorRepository,
                          BookOrderRepository bookOrderRepository,
                          CustomerRepository customerRepository,
                          IntToStringService intToStringService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookOrderRepository = bookOrderRepository;
        this.customerRepository = customerRepository;
        this.intToStringService = intToStringService;
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
            throw new IllegalArgumentException("Received BookRequest is null");
        }
        Author author = authorRepository.findByName(bookRequest.getAuthor());
        bookRepository.save(new Book(bookRequest.getISBNNumber(), bookRequest.getTitle(), BOOK_QUANTITY, BookGenre.getBookGenreValue(bookRequest.getBookGenre()), author));
    }

    @Override
    public void deleteBookEntity(Integer isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("Received isbn is null");
        }
        bookRepository.delete(bookRepository.findByISBNNumber(isbn));
    }

    @Override
    public void saveAuthorEntity(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Received name is null");
        }
        authorRepository.save(new Author(name));
    }

    @Override
    public Book findBookById(Integer isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("Received isbn is null");
        }
        return bookRepository.findByISBNNumber(isbn);
    }

    @Override
    public List<Book> search(String search) {
        Set<Book> result = new HashSet<>();
        try {
            if (bookRepository.findById(intToStringService.convertToInteger(search)).isPresent()){
                result.add(bookRepository.findByISBNNumber(intToStringService.convertToInteger(search)));
            }
        } catch (IllegalArgumentException ignored) {
        }
        result.addAll(bookRepository.findByTitleContaining(search));
        result.addAll(bookRepository.findByAuthorNameContaining(search));
        return new ArrayList<>(result);
    }

    @Override
    public void updateBook(BookRequest bookRequest) {
        if (bookRequest == null) {
            throw new IllegalArgumentException("Null value added as parameter");
        }
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
        if (isbn == null || customerId == null) {
            throw new IllegalArgumentException("Null value added as parameter");
        }
        if (isCustomerExist(customerId)) {
            if (isBookOrderAble(isbn)) {
                Customer orderingCustomer = customerRepository.getById(customerId);
                Book preOrderedBook = bookRepository.findByISBNNumber(isbn);
                preOrderedBook.setQuantity(preOrderedBook.getQuantity() - 1);
                bookRepository.save(preOrderedBook);
                bookOrderRepository.save(new BookOrder(preOrderedBook, orderingCustomer));
            } else {
                throw new NoSuchElementException("Book not available to take from library");
            }
        } else throw new NoSuchElementException("No customer found with this id");
    }

    @Override
    public List<BookOrder> findAllBookOrder() {
        return bookOrderRepository.findAll();
    }

    @Override
    public void returnBook(Integer orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Null value added as parameter");
        }
        BookOrder orderBookToReturn;
        if (bookOrderRepository.findById(orderId).isPresent()) {
            orderBookToReturn = bookOrderRepository.findById(orderId).get();
            Book returningBook = bookRepository.findByISBNNumber(orderBookToReturn.getBook().getISBNNumber());
            returningBook.setQuantity(returningBook.getQuantity() + 1);
            bookRepository.save(returningBook);
            bookOrderRepository.delete(orderBookToReturn);
        } else {
            throw new NoSuchElementException ("No order found with this id");
        }
    }

    private boolean isBookOrderAble(Integer isbn) {
        boolean isOrderAble = false;
        if (bookRepository.findById(isbn).isPresent()) {
            isOrderAble = bookRepository.findById(isbn).get().getQuantity() >= 1;
        }
        return isOrderAble;
    }

    private boolean isCustomerExist(Integer customerId) {
        return customerRepository.findById(customerId).isPresent();
    }
}
