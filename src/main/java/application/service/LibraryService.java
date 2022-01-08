package application.service;

import application.domain.Author;
import application.domain.Book;
import application.domain.BookRequest;
import application.controller.HomeController;

import java.util.List;

/**
 * Service interface for {@link HomeController};
 */
public interface LibraryService {

    List<Author> findAllAuthors();

    List<Book> findAllBooks();

    void saveBookEntity(BookRequest bookRequest);

    void deleteBookEntity(Integer isbn);

    void saveAuthorEntity(String name);

    List<Book> searchBookByName(String search);

    List<Book> searchBookByAuthor(String search);

    List<Book> searchBookByISBN(String search);

    List<Book> search(String search);

}
