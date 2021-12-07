package application.repository;

import application.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for JPA operators for {@link Book} objects.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    //@Query("SELECT book FROM Book book WHERE CONCAT(book.bookId, book.title, book.ISBNNumber, book.bookGenre) LIKE %?1%")
    //List<Book> search(String keyword);

    @Query("SELECT book FROM Book book WHERE book.ISBNNumber LIKE %?1%")
    Book findBook(String isbn);
}
