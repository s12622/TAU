package pl.edu.pjwstk.zad11.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.pjwstk.zad11.domain.Author;
import pl.edu.pjwstk.zad11.domain.Book;

import javax.persistence.Id;

//@Ignore

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@Rollback
//@Commit
@Transactional(transactionManager = "txManager")
public class PublishingManagerTest {

    @Autowired
    PublishingManager publishingManager;

    private final String NAME_1 = "Paulo Coelho";
    private final String IDNUMBER_1 = "1234";

    private final String NAME_2 = "Stephen King";
    private final String IDNUMBER_2 = "5678";

    private final String TITLE_1 = "The Alchemist";
    private final String ISBN_1 = "978-0061122415";

    private final String TITLE_2 = "It";
    private final String ISBN_2 = "978-0451169518";

    @Test
    public void addAuthorCheck() {

        List<Author> retrievedAuthors = publishingManager.getAllAuthors();

        for(Author author: retrievedAuthors) {
            System.out.println("Author: " + author.getName() + ". Numer autora: " + author.getIdNumber());
            if(author.getIdNumber().equals(IDNUMBER_1)) {
                publishingManager.deleteAuthor(author);
            }
        }

        retrievedAuthors = publishingManager.getAllAuthors();

        Author author = new Author();
        author.setName(NAME_1);
        author.setIdNumber(IDNUMBER_1);

        publishingManager.addAuthor(author);

        assertNotNull(retrievedAuthors);

        Author retrievedAuthor = publishingManager.findAuthorByIdNumber(IDNUMBER_1);

        assertEquals(NAME_1, retrievedAuthor.getName());
        assertEquals(IDNUMBER_1, retrievedAuthor.getIdNumber());
    }

    @Test
    public void addBookCheck() {

        Book book = new Book();
        book.setIsbn(ISBN_1);
        book.setTitle(TITLE_1);

        Long bookId = publishingManager.addNewBook(book);

        Book retrievedBook = publishingManager.findBookById(bookId);
        assertNotNull(retrievedBook);
        assertEquals(ISBN_1, retrievedBook.getIsbn());
        assertEquals(TITLE_1, retrievedBook.getTitle());
    }

    @Test
    public void approveBookCheck() {
        Author author = new Author();
        author.setName(NAME_2);
        author.setIdNumber(IDNUMBER_2);
        publishingManager.addAuthor(author);

        Author retrievedAuthor = publishingManager.findAuthorByIdNumber(IDNUMBER_2);

        Book book = new Book();
        book.setIsbn(ISBN_2);
        book.setTitle(TITLE_2);

        Long bookId = publishingManager.addNewBook(book);
        publishingManager.approveBook(retrievedAuthor.getId(), bookId);

        List<Book> books = publishingManager.getAuthorsBooks(retrievedAuthor);

        assertEquals(1, books.size());
        assertEquals(ISBN_2, books.get(0).getIsbn());
        assertEquals(TITLE_2, books.get(0).getTitle());

    }

    @Test
    public void deleteBookCheck() {
        //Add book
        Book book = new Book();
        book.setIsbn(ISBN_2);
        book.setTitle(TITLE_2);

        Long bookId = publishingManager.addNewBook(book);

        Book retrievedBook = publishingManager.findBookById(bookId);
        assertNotNull(retrievedBook);
        assertEquals(ISBN_2, retrievedBook.getIsbn());
        assertEquals(TITLE_2, retrievedBook.getTitle());

        //Add author
        Author author = new Author();
        author.setName(NAME_2);
        author.setIdNumber(IDNUMBER_2);
        publishingManager.addAuthor(author);
        Author retrievedAuthor = publishingManager.findAuthorByIdNumber(IDNUMBER_2);


        //Approve author's book
        publishingManager.approveBook(retrievedAuthor.getId(), bookId);

        List<Book> books = publishingManager.getAuthorsBooks(retrievedAuthor);
        assertEquals(NAME_2, retrievedAuthor.getName());
        assertEquals(IDNUMBER_2, retrievedAuthor.getIdNumber());

        List<Book> allBooks = publishingManager.getAllBooks();

        assertEquals(1, books.size());


        //Delete
        Book bookToDelete = publishingManager.findBookById(retrievedBook.getId());
        publishingManager.deleteBook(retrievedAuthor, bookToDelete);
        assertEquals(false, bookToDelete.getApproved());

        assertEquals(0, retrievedAuthor.getBooks().size());
        assertEquals(1, publishingManager.getAllBooks().size());
    }

    @Test
    public void updateAuthorCheck() {
        Author author = new Author();
        author.setName(NAME_1);
        author.setIdNumber(IDNUMBER_1);
        publishingManager.addAuthor(author);
        Author retrievedAuthor = publishingManager.findAuthorByIdNumber(IDNUMBER_1);

        retrievedAuthor.setName("Andrzej Sapkowski");
        retrievedAuthor.setIdNumber("1337");

        publishingManager.updateAuthor(retrievedAuthor);


        assertEquals("Andrzej Sapkowski", author.getName());
        assertEquals("1337", author.getIdNumber());
    }

    @Test
    public void deleteAuthorCheck() {
        Author author = new Author();
        author.setName(NAME_1);
        author.setIdNumber(IDNUMBER_1);
        publishingManager.addAuthor(author);
        Author retrievedAuthor = publishingManager.findAuthorByIdNumber(IDNUMBER_1);
        List<Author> allAuthors = publishingManager.getAllAuthors();

        Book book = new Book();
        book.setIsbn(ISBN_2);
        book.setTitle(TITLE_2);

        Long bookId = publishingManager.addNewBook(book);

        publishingManager.approveBook(retrievedAuthor.getId(), bookId);

        Book authorsBook = publishingManager.findBookById(bookId);
        assertEquals(true, authorsBook.getApproved());
        assertEquals(1, publishingManager.getAllAuthors().size());
        publishingManager.deleteAuthor(retrievedAuthor);
        assertEquals(false, authorsBook.getApproved());
        assertEquals(0, publishingManager.getAllAuthors().size());
    }

}
