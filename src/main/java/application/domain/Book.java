package application.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

/**
 * Domain class for {@link Book} objects.
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ISBNNumber;
    private String title;
    private BookGenre bookGenre;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId", referencedColumnName = "author_Id")
    private Author author;

    public Book(Integer ISBNNumber, String title, BookGenre bookGenre, Author author) {
        this.ISBNNumber = ISBNNumber;
        this.title = title;
        this.bookGenre = bookGenre;
        this.author = author;
    }

    public String getName() {
        return title;
    }

    public void setName(String title) {
        this.title = title;
    }

    public Integer getISBNNumber() {
        return ISBNNumber;
    }

    public void setISBNNumber(Integer ISBNNumber) {
        this.ISBNNumber = ISBNNumber;
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(ISBNNumber, book.ISBNNumber) && Objects.equals(title, book.title) && bookGenre == book.bookGenre && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBNNumber, title, bookGenre, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBNNumber=" + ISBNNumber +
                ", title='" + title + '\'' +
                ", bookGenre=" + bookGenre +
                ", author=" + author +
                '}';
    }

}
