package application.service;

import application.domain.Book;
import application.domain.BookRequest;

import java.util.List;

/**
 * Service interface for {@link Book} related operations.
 */
public interface BookService {

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
     * Get a {@link Book} from the database.
     * @param isbn shows which book entity should be return.
     * @return the book entity.
     */
    Book findBookById(Integer isbn);

    /**
     * Update a {@link Book} entity in the database.
     * @param bookRequest is an entity which will be used to update {@link Book} object.
     */
    void updateBook(BookRequest bookRequest);

    /**
     * Get a list of {@link Book}s from the database.
     * @param search shows which book entities should be return.
     * @return the list of book matches search criteria.
     */
    List<Book> search(String search);
}
