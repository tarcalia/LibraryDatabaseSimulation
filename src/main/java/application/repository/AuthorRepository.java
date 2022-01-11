package application.repository;

import application.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for JPA operators for {@link Author} objects.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findByName(String name);

}
