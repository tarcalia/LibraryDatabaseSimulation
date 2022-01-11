package application.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import java.util.Objects;

/**
 * Domain class for {@link Book} objects.
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    private Integer ISBNNumber;
    private String title;
    private BookGenre bookGenre;
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "authorId", referencedColumnName = "author_Id")
    private Author author;

    public Book() {
    }

    public Book(Integer ISBNNumber, String title, Integer quantity, BookGenre bookGenre, Author author) {
        this.ISBNNumber = ISBNNumber;
        this.title = title;
        this.quantity = quantity;
        this.bookGenre = bookGenre;
        this.author = author;
    }

    public Integer getISBNNumber() {
        return ISBNNumber;
    }

    public void setISBNNumber(Integer ISBNNumber) {
        this.ISBNNumber = ISBNNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
