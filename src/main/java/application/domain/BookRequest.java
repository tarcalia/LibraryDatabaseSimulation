package application.domain;

import java.util.Objects;
import application.controller.HomeController;

/**
 * Domain class for {@link BookRequest} object to for {@link HomeController}.
 */
public class BookRequest {
    private Integer ISBNNumber;
    private String title;
    private Integer quantity;
    private String bookGenre;
    private String author;

    public BookRequest() {
    }

    public BookRequest(Integer ISBNNumber, String title, Integer quantity, String bookGenre, String author) {
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

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRequest that = (BookRequest) o;
        return Objects.equals(ISBNNumber, that.ISBNNumber) && Objects.equals(title, that.title) && Objects.equals(quantity, that.quantity) && Objects.equals(bookGenre, that.bookGenre) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBNNumber, title, quantity, bookGenre, author);
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "ISBNNumber=" + ISBNNumber +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", bookGenre='" + bookGenre + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
