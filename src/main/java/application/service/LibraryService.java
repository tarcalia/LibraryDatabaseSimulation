package application.service;

import application.domain.Author;
import application.domain.Book;
import application.domain.BookGenre;
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

    Book findBookById(String search);

    List<Book> search(String search);

    void updateBook(BookRequest bookRequest);

}
