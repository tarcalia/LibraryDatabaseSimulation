package application.repository;

import application.domain.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for JPA operators for {@link BookOrder} objects.
 */
@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {
}
