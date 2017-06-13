package pl.edu.pjwstk.zad11.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "author.all", query = "Select a from Author a"),
        @NamedQuery(name = "author.byIdNumber", query = "Select a from Author a where a.idNumber = :idNumber")
})
public class Author {

    private Long id;
    private String name;
    private String idNumber;
    private List<Book> books;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    @Column(unique = true, nullable = false)
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
