package pl.edu.pjwstk.zad11.service;


import pl.edu.pjwstk.zad11.domain.Author;
import pl.edu.pjwstk.zad11.domain.Book;

import java.util.List;

public interface PublishingManager {

    void addAuthor(Author author);
    List<Author> getAllAuthors();
    void deleteAuthor(Author author);
    Author findAuthorByIdNumber(String idNumber);

    Long addNewBook(Book book);
    List<Book> getAllBooks();
    void deleteBook(Author author, Book book);
    Book findBookById(Long id);

    List<Book> getAuthorsBooks(Author author);
    void approveBook(Long authorId, Long bookId);

    void updateAuthor(Author author);
}
