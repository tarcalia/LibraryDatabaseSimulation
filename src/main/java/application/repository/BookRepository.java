package application.repository;

import application.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface for JPA operators for {@link Book} objects.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByISBNNumber(Integer isbn);

    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorNameContaining(String name);
}
