package application.service;

import application.domain.Author;

import java.util.List;

/**
 * Interface for {@link Author} related services.
 */
public interface AuthorService {

    /**
     * Add an {@link Author} object to the repository.
     * @param author which will be added.
     */
    void addAuthor(Author author);

    /**
     * Delete an {@link Author} object from the repository.
     * @param author which will be deleted.
     */
    void deleteAuthor(Author author);

    /**
     * Returns a list of all {@link Author}s.
     * @return a list object containing Authors.
     */
    List<Author> findAllUsers();

}
