package pl.edu.pjwstk.zad11.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "book.notApproved", query = "SELECT b FROM Book b WHERE b.approved = false")
})
public class Book {

    private Long id;
    private String isbn;
    private String title;
    private Boolean approved = false;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean finished) {
        this.approved = finished;
    }
}
