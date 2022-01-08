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
import java.util.stream.Collectors;

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
        bookRepository.save(new Book(bookRequest.getISBNNumber(), bookRequest.getTitle(), BookGenre.getBookGenreValue(bookRequest.getBookGenre()), author));
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
    public List<Book> searchBookByName(String search) {
        return bookRepository.findAll().stream().filter(b -> b.getTitle().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBookByAuthor(String search) {
        return bookRepository.findAll().stream().filter(a -> a.getAuthor().getName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBookByISBN(String search) {
        return bookRepository.findAll().stream().filter(b -> b.getISBNNumber().toString().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Book> search(String search) {
        Set<Book> result = new HashSet<>();
        result.addAll(searchBookByISBN(search));
        result.addAll(searchBookByName(search));
        result.addAll(searchBookByAuthor(search));
        return new ArrayList<>(result);
    }

}
