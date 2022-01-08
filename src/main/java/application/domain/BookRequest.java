package application.domain;

import java.util.Objects;
import application.controller.HomeController;

/**
 * Domain class for {@link BookRequest} object to for {@link HomeController}.
 */
public class BookRequest {
    private Integer ISBNNumber;
    private String title;
    private String bookGenre;
    private String author;

    public BookRequest() {
    }

    public BookRequest(Integer ISBNNumber, String title, String bookGenre, String author) {
        this.ISBNNumber = ISBNNumber;
        this.title = title;
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
        BookRequest bookRequest = (BookRequest) o;
        return Objects.equals(ISBNNumber, bookRequest.ISBNNumber) && Objects.equals(title, bookRequest.title) && bookGenre == bookRequest.bookGenre && Objects.equals(author, bookRequest.author);
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
