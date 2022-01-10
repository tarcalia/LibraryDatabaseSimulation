package application.service.impl;

import application.domain.Author;
import application.domain.Book;
import application.domain.BookGenre;
import application.domain.Customer;
import application.domain.BookOrder;
import application.repository.BookOrderRepository;
import application.repository.BookRepository;
import application.repository.CustomerRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderHandlerTest {
    private static final Author AUTHOR_1 = new Author("William Shakespeare");
    private static final Author AUTHOR_2 = new Author("Agatha Christie");
    private static final Author AUTHOR_3 = new Author("J. K. Rowling");
    private static final Integer ISBN_1 = 1234;
    private static final Book BOOK_1 = new Book(ISBN_1, "Hamlet", 4, BookGenre.CLASSIC, AUTHOR_1);
    private static final Book BOOK_2 = new Book(2345, "Poirot", 3, BookGenre.FICTION, AUTHOR_2);
    private static final Integer EXPECTED_QUANTITY = 3;
    private static final String CUSTOMER_NAME = "TEST NAME";
    private static final String CUSTOMER_NAME_2 = "NAME TEST";
    private static final Customer CUSTOMER_1 = new Customer(CUSTOMER_NAME);
    private static final Customer CUSTOMER_2 = new Customer(CUSTOMER_NAME_2);
    private static final Integer CUSTOMER_ID_1 = 1;
    private static final BookOrder ORDER_1 = new BookOrder(BOOK_1, CUSTOMER_1);
    private static final BookOrder ORDER_2 = new BookOrder(BOOK_2, CUSTOMER_2);
    private static final Integer ORDER_ID = 1;
    private static final List<BookOrder> RESULT_LIST = new ArrayList<>(Arrays.asList(ORDER_1, ORDER_2));

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookOrderRepository bookOrderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderHandler underTest;

    @BeforeMethod
    public void setUp() {
        ORDER_1.setOrderId(ORDER_ID);
        CUSTOMER_1.setCustomerId(CUSTOMER_ID_1);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOrderBook() {
        //given
        when(customerRepository.getById(CUSTOMER_ID_1)).thenReturn(CUSTOMER_1);
        when(customerRepository.findById(CUSTOMER_ID_1)).thenReturn(Optional.of(CUSTOMER_1));
        when(bookRepository.findByISBNNumber(ISBN_1)).thenReturn(BOOK_1);
        when(bookRepository.findById(ISBN_1)).thenReturn(Optional.of(BOOK_1));
        //when
        underTest.orderBook(ISBN_1, CUSTOMER_ID_1);
        //then
        assertThat(BOOK_1.getQuantity(), equalTo(EXPECTED_QUANTITY));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testOrderBookWithNullValue() {
        //given
        //when
        underTest.orderBook(null, null);
        //then
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testOrderBookCustomerNotFound() {
        //given
        when(customerRepository.findById(CUSTOMER_ID_1)).thenReturn(Optional.empty());
        //when
        underTest.orderBook(ISBN_1, CUSTOMER_ID_1);
        //then
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testOrderBookNoBookAvailableToOrder() {
        //given
        when(customerRepository.getById(CUSTOMER_ID_1)).thenReturn(CUSTOMER_1);
        when(bookRepository.findById(ISBN_1)).thenReturn(Optional.empty());
        //when
        underTest.orderBook(ISBN_1, CUSTOMER_ID_1);
        //then
    }

    @Test
    public void testFindAllBookOrder() {
        //given
        when(bookOrderRepository.findAll()).thenReturn(RESULT_LIST);
        //when
        List<BookOrder> result = underTest.findAllBookOrder();
        //then
        assertThat(result, equalTo(RESULT_LIST));
    }

    @Test
    public void testReturnBook() {
        //given
        when(bookRepository.findById(ISBN_1)).thenReturn(Optional.of(BOOK_1));
        when(bookOrderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_1));
        when(bookRepository.findByISBNNumber(BOOK_1.getISBNNumber())).thenReturn(BOOK_1);
        //when
        underTest.returnBook(ORDER_ID);
        //then
        verify(bookOrderRepository).delete(ORDER_1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testReturnBookWithNullValue() {
        //given
        //when
        underTest.returnBook(null);
        //then
    }
}
