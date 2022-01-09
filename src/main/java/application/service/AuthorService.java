package application.service;

import application.domain.Author;

import java.util.List;

/**
 * Interface for {@link Author} operations.
 */
public interface AuthorService {

    /**
     * Save a {@link Author} entity to the database.
     * @param name is the name of the author.
     */
    void saveAuthorEntity(String name);

    /**
     * Get all book {@link Author}s.
     * @return authors in a list.
     */
    List<Author> findAllAuthors();
}
