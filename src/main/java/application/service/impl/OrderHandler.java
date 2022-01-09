package application.service.impl;

import application.domain.BookOrder;
import application.domain.Customer;
import application.repository.BookOrderRepository;
import application.repository.BookRepository;
import application.repository.CustomerRepository;
import application.service.OrderService;
import application.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service class for {@link Book} order/return operations.
 */
@Service
public class OrderHandler implements OrderService {
    private BookRepository bookRepository;
    private BookOrderRepository bookOrderRepository;
    private CustomerRepository customerRepository;

    public OrderHandler(BookRepository bookRepository, BookOrderRepository bookOrderRepository, CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.bookOrderRepository = bookOrderRepository;
        this.customerRepository = customerRepository;
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
            throw new NoSuchElementException("No order found with this id");
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
