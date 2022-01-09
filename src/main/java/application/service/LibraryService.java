package application.service;

import application.domain.Author;
import application.domain.Book;
import application.domain.BookOrder;
import application.domain.BookRequest;
import application.controller.LibraryController;

import java.util.List;

/**
 * Service interface for {@link LibraryController};
 */
public interface LibraryService {

    /**
     * Get all book {@link Author}s.
     * @return authors in a list.
     */
    List<Author> findAllAuthors();

    /**
     * Get all {@link Book}s from the database.
     * @return books in a list.
     */
    List<Book> findAllBooks();

    /**
     * Save a {@link Book} entity to the database.
     * @param bookRequest is an entity which will be used to create & save {@link Book} entity.
     */
    void saveBookEntity(BookRequest bookRequest);

    /**
     * Delete a {@link Book} entity from the database.
     * @param isbn shows which book entity should be deleted.
     */
    void deleteBookEntity(Integer isbn);

    /**
     * Save a {@link Author} entity to the database.
     * @param name is the name of the author.
     */
    void saveAuthorEntity(String name);

    /**
     * Get a {@link Book} from the database.
     * @param isbn shows which book entity should be return.
     * @return the book entity.
     */
    Book findBookById(Integer isbn);

    /**
     * Get a list of {@link Book}s from the database.
     * @param search shows which book entities should be return.
     * @return the list of book matches search criteria.
     */
    List<Book> search(String search);

    /**
     * Update a {@link Book} entity in the database.
     * @param bookRequest is an entity which will be used to update {@link Book} object.
     */
    void updateBook(BookRequest bookRequest);

    /**
     * Order/Take a {@link Book} from the library.
     * @param isbn represents the ISBN of the book.
     * @param customerId represents the unique ID of a customer who borrow the book.
     */
    void orderBook(Integer isbn, Integer customerId);

    /**
     * Returns all taken {@link Book}s from the library.
     * @return a list of taken books.
     */
    List<BookOrder> findAllBookOrder();

    /**
     * Returns a {@link Book} to the library
     * @param orderId is a unique id of each borrowed books.
     */
    void returnBook(Integer orderId);
}
