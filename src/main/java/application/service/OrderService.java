package application.service;

import application.domain.Book;
import application.domain.BookOrder;

import java.util.List;

/**
 * Interface for {@link Book} order/return operations.
 */
public interface OrderService {
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
