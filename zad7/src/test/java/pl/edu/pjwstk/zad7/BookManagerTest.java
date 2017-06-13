package pl.edu.pjwstk.zad7;

import static org.junit.Assert.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import org.springframework.util.Assert;
import pl.edu.pjwstk.zad7.domain.Book;
import pl.edu.pjwstk.zad7.service.BookManagerImpl;

public class BookManagerTest {

    BookManagerImpl bookManager = new BookManagerImpl();


    private final static long ID_1 = 1;
    private final static long ID_2 = 2;

    private final static String AUTHOR_1 = "Paulo Coelho";
    private final static String TITLE_1 = "The Alchemist";
    private final static String ISBN_1 = "0061122416";

    private final static String AUTHOR_2 = "Caulo Poelho";
    private final static String TITLE_2 = "Tested";
    private final static String ISBN_2 = "0601123517";

    public BookManagerTest() throws SQLException {
    }

    @After
    public void cleanup() throws SQLException {
        bookManager.clearBooks();
    }

    @Test
    public void checkConnection() {
        assertNotNull(bookManager.getConnection());
    }

    @Test
    public void checkAllBooks() {
        List<Book> books = new ArrayList<Book>();
        books = bookManager.getAllBooks();
        assertNotNull(books);
    }

    @Test
    public void checkAdding() throws SQLException {
        Book book = new Book();

        book.setAuthor(AUTHOR_1);
        book.setTitle(TITLE_1);
        book.setIsbn(ISBN_1);

        assertEquals(1, bookManager.addBook(book));

        book.setAuthor(AUTHOR_2);
        book.setTitle(TITLE_2);
        book.setIsbn(ISBN_2);

        assertEquals(1, bookManager.addBook(book));

        List<Book> books = bookManager.getAllBooks();

        Book bookRetrieved = books.get(0);

        assertEquals(AUTHOR_1, bookRetrieved.getAuthor());
        assertEquals(TITLE_1, bookRetrieved.getTitle());
        assertEquals(ISBN_1, bookRetrieved.getIsbn());

        bookRetrieved = books.get(1);

        assertEquals(AUTHOR_2, bookRetrieved.getAuthor());
        assertEquals(TITLE_2, bookRetrieved.getTitle());
        assertEquals(ISBN_2, bookRetrieved.getIsbn());
        assertEquals(2, books.size());
    }

    @Test
    public void checkGettingBook() {

        Book book = new Book();

        book.setAuthor(AUTHOR_1);
        book.setTitle(TITLE_1);
        book.setIsbn(ISBN_1);

        assertEquals(1, bookManager.addBook(book));

        book.setAuthor(AUTHOR_2);
        book.setTitle(TITLE_2);
        book.setIsbn(ISBN_2);

        assertEquals(1, bookManager.addBook(book));

        assertEquals(AUTHOR_1, bookManager.getBook(ID_1).getAuthor());
        assertEquals(TITLE_1, bookManager.getBook(ID_1).getTitle());
        assertEquals(ISBN_1, bookManager.getBook(ID_1).getIsbn());
    }

    @Test
    public void deleteBook() {
        Book book = new Book();
        book.setAuthor(AUTHOR_1);
        book.setTitle(TITLE_1);
        book.setIsbn(ISBN_1);

        assertEquals(1, bookManager.addBook(book));
        assertEquals(1, bookManager.getAllBooks().size());

        book.setAuthor(AUTHOR_2);
        book.setTitle(TITLE_2);
        book.setIsbn(ISBN_2);

        assertEquals(1, bookManager.addBook(book));
        assertEquals(2, bookManager.getAllBooks().size());

        bookManager.deleteBook(ID_1);
        assertEquals(1, bookManager.getAllBooks().size());
        bookManager.deleteBook(ID_2);
        assertEquals(0, bookManager.getAllBooks().size());
    }

    @Test
    public void updateBook() {
        Book book = new Book();
        book.setAuthor(AUTHOR_1);
        book.setTitle(TITLE_1);
        book.setIsbn(ISBN_1);

        assertEquals(1, bookManager.addBook(book));
        assertEquals(1, bookManager.getAllBooks().size());

        book.setAuthor(AUTHOR_2);
        book.setTitle(TITLE_2);
        book.setIsbn(ISBN_2);
        book.setId(ID_1);

        bookManager.updateBook(book);

        assertEquals(AUTHOR_2, bookManager.getBook(ID_1).getAuthor());
        assertEquals(TITLE_2, bookManager.getBook(ID_1).getTitle());
        assertEquals(ISBN_2, bookManager.getBook(ID_1).getIsbn());
    }

}