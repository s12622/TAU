package pl.edu.pjwstk.zad7.domain;


public class Book {

    private long id;
    private String author;
    private String title;
    private String isbn;

    public Book() {
    }

    public Book(String author, String title, String isbn) {
        super();
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
