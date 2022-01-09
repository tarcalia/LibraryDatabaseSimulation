package application.service.impl;

import application.repository.AuthorRepository;
import application.service.AuthorService;
import org.springframework.stereotype.Service;
import application.domain.Author;

import java.util.List;

/**
 * Service class for {@link Author} operations.
 */
@Service
public class AuthorHandler implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorHandler(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthorEntity(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Received name is null");
        }
        authorRepository.save(new Author(name));
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
}
