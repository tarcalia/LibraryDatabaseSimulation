package application.service.impl;

import application.domain.Author;
import application.domain.Book;
import application.domain.BookGenre;
import application.domain.BookRequest;
import application.repository.AuthorRepository;
import application.repository.BookRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Unit tests for {@link BookHandler} class.
 */
public class BookHandlerTest {
    private static final Author AUTHOR_1 = new Author("William Shakespeare");
    private static final Author AUTHOR_2 = new Author("Agatha Christie");
    private static final Author AUTHOR_3 = new Author("J. K. Rowling");
    private static final Integer ISBN_1 = 1234;
    private static final Book BOOK_1 = new Book(ISBN_1, "Hamlet", 4, BookGenre.CLASSIC, AUTHOR_1);
    private static final Book BOOK_2 = new Book(2345, "Poirot", 3, BookGenre.FICTION, AUTHOR_2);
    private static final Book BOOK_3 = new Book(5678, "Harry Potter", 2, BookGenre.DOCUMENTARY, AUTHOR_3);
    private static final List<Book> REPOSITORY_LIST = new ArrayList<>(Arrays.asList(BOOK_1, BOOK_2, BOOK_3));
    private static final List<Book> RESULT_LIST_1 = new ArrayList<>(List.of(BOOK_1));
    private static final List<Book> RESULT_LIST_2 = new ArrayList<>(List.of(BOOK_2));
    private static final List<Book> RESULT_LIST_3 = new ArrayList<>(List.of(BOOK_3));
    private static final BookRequest BOOK_REQUEST = new BookRequest(BOOK_1.getISBNNumber(),
                                                                    BOOK_1.getTitle(), BOOK_1.getQuantity(),
                                                                    BOOK_1.getBookGenre().label, BOOK_1.getAuthor().getName());

    @Mock
    AuthorRepository authorRepository;

    @Mock
    BookRepository bookRepository;

    @Mock
    IntToStringConverter intToStringConverter;

    @InjectMocks
    private BookHandler underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllBooks() {
        //given
        when(bookRepository.findAll()).thenReturn(REPOSITORY_LIST);
        //when
        List<Book> result = underTest.findAllBooks();
        //then
        assertThat(result, equalTo(REPOSITORY_LIST));
    }

    @Test
    public void testFindBookById() {
        //given
        when(bookRepository.findByISBNNumber(ISBN_1)).thenReturn(BOOK_1);
        //when
        Book result = underTest.findBookById(ISBN_1);
        //then
        assertThat(result, equalTo(BOOK_1));
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindBookByIdWithNullValue() {
        //given
        //when
        underTest.findBookById(null);
        //then
    }

    @Test
    public void testUpdateBook() {
        //given
        Book bookRequestFromUpdate = BOOK_1;
        when(bookRepository.findByISBNNumber(BOOK_REQUEST.getISBNNumber())).thenReturn(bookRequestFromUpdate);
        when(authorRepository.findByName(bookRequestFromUpdate.getAuthor().getName())).thenReturn(BOOK_1.getAuthor());
        //when
        underTest.updateBook(BOOK_REQUEST);
        //then
        assertThat(bookRequestFromUpdate, equalTo(BOOK_1));
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateBookWithNullValue() {
        //given
        //when
        underTest.updateBook(null);
        //then
    }

    @Test
    public void testSearchByTitle() {
        //given
        when(bookRepository.findByAuthorNameContaining(BOOK_2.getTitle())).thenReturn(RESULT_LIST_2);
        //when
        List<Book> result = underTest.search(BOOK_2.getTitle());
        //then
        assertThat(result, equalTo(RESULT_LIST_2));
    }
    @Test
    public void testSearchByAuthor() {
        //given
        when(bookRepository.findByTitleContaining(BOOK_3.getAuthor().getName())).thenReturn(RESULT_LIST_3);
        //when
        List<Book> result = underTest.search(BOOK_3.getAuthor().getName());
        //then
        assertThat(result, equalTo(RESULT_LIST_3));
    }

    @Test
    public void testSearchByISBN() {
        //given
        when(bookRepository.findById((ISBN_1))).thenReturn(Optional.of(BOOK_1));
        when(bookRepository.findByISBNNumber(ISBN_1)).thenReturn(BOOK_1);
        when(intToStringConverter.convertToInteger(String.valueOf(ISBN_1))).thenReturn(ISBN_1);
        //when
        List<Book> result = underTest.search(String.valueOf(BOOK_1.getISBNNumber()));
        //then
        assertThat(result, equalTo(RESULT_LIST_1));
    }

    @Test
    public void testSearchWithNullValue() {
        //given
        //when
        underTest.search(null);
        //then
    }

    @DataProvider(name = "dataProviderForTestSearch")
    public Object[][] sampleResults() {
        return new Object [][] {
                {BOOK_2.getTitle(), RESULT_LIST_2},
                {BOOK_3.getAuthor().getName(), RESULT_LIST_3},
        };
    }
}
