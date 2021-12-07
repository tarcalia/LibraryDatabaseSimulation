package application.repository;

import application.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface for JPA operators for {@link Author} objects.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT name FROM Author WHERE name = :name")
    Author findByName(@Param("name") String name);

}
