package application.service.impl;

import application.domain.Author;
import application.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for {@link Author} operations.
 */
@Service
public class AuthorRepositoryService implements AuthorService {

    @Override
    public void addAuthor(Author author) {

    }

    @Override
    public void deleteAuthor(Author author) {

    }

    @Override
    public List<Author> findAllUsers() {
        return null;
    }
}
