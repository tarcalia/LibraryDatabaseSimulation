package application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

/**
 * Domain class for {@link Author} objects.
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_Id")
    private Long authorId;
    private String name;
    private boolean popular;

    @JsonBackReference
    @OneToMany(mappedBy = "author")
    private List<Book> book;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return popular == author.popular && Objects.equals(authorId, author.authorId) && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, name, popular);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", popular=" + popular +
                '}';
    }

}
