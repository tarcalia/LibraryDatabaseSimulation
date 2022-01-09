package application.service.impl;

import application.domain.Author;
import application.domain.Book;
import application.domain.BookGenre;
import application.domain.BookRequest;
import application.repository.AuthorRepository;
import application.repository.BookRepository;
import application.service.BookService;
import application.service.IntToStringService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service class to handle {@link Book} objects.
 */
@Service
public class BookHandler implements BookService {
    private final Integer BOOK_QUANTITY = 1;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private IntToStringService intToStringService;

    public BookHandler(BookRepository bookRepository, AuthorRepository authorRepository, IntToStringService intToStringService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.intToStringService = intToStringService;
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
    public Book findBookById(Integer isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("Received isbn is null");
        }
        return bookRepository.findByISBNNumber(isbn);
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
}
