package application.service.impl;

import application.domain.Author;
import application.domain.Book;
import application.domain.BookGenre;
import application.domain.BookRequest;
import application.repository.AuthorRepository;
import application.repository.BookRepository;
import application.service.LibraryService;
import org.springframework.stereotype.Service;
import application.controller.HomeController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service class for {@link HomeController}.
 */
@Service
public class LibraryHandler implements LibraryService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public LibraryHandler(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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

}
