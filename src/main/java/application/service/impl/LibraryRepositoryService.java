package application.service.impl;

import application.domain.Book;
import application.domain.BookGenre;
import application.service.LibraryService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service class for {@link LibraryRepositoryService} operations.
 */
@Service
public class LibraryRepositoryService implements LibraryService {

    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public Book findBookByISBN(Integer isbnNumber) {
        return null;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> findBooksByGenre(Enum<BookGenre> genre) {
        return null;
    }
}
