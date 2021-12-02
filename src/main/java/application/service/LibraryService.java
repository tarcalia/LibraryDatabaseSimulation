package application.service;

import application.domain.Book;
import application.domain.BookGenre;

import java.util.List;

public interface LibraryService {

    Book findBookByTitle(String title);

    Book findBookByISBN(Integer isbnNumber);

    List<Book> findBooksByAuthor(String author);

    List<Book> findBooksByGenre(Enum<BookGenre> genre);
}
