package application.service;

import application.domain.Book;

public interface BookService {

    /**
     * Add a {@link Book} object to the repository.
     * @param book will be added.
     */
    void addBook(Book book);

    /**
     * Delete a {@link Book} from the repository.
     * @param id of the book which will be deleted.
     */
    void deleteBook(Long id);
}
