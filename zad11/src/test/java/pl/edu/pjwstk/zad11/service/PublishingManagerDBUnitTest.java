package pl.edu.pjwstk.zad11.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.pjwstk.zad11.domain.Author;
import pl.edu.pjwstk.zad11.domain.Book;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
@Rollback
//@Commit
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class PublishingManagerDBUnitTest {

    @Autowired
    PublishingManager publishingManager;

    @Test
    @DatabaseSetup("/fullData.xml")
    @ExpectedDatabase(value = "/addAuthorData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)

    public void addAuthorCheck() {
        assertEquals(2, publishingManager.getAllAuthors().size());

        Author a = new Author();
        a.setName("J. K. Rowling");
        a.setIdNumber("9101");

        publishingManager.addAuthor(a);
        assertEquals(3, publishingManager.getAllAuthors().size());
    }

    @Test
    @DatabaseSetup("/fullData.xml")
    @ExpectedDatabase(value = "/deleteAuthorData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteAuthorCheck() {
        assertEquals(2, publishingManager.getAllAuthors().size());
        Author author = publishingManager.findAuthorByIdNumber("5678");
        publishingManager.deleteAuthor(author);

        assertEquals(1, publishingManager.getAllAuthors().size());
    }

    @Test
    @DatabaseSetup("/fullData.xml")
    @ExpectedDatabase(value = "/updateAuthorData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateAuthorCheck() {
        Author author = publishingManager.findAuthorByIdNumber("5678");
        System.out.println(author.getName());
        assertEquals("Stephen King", author.getName());
        author.setName("Andrzej Sapkowski");
        publishingManager.updateAuthor(author);
        System.out.println(author.getName());
        assertEquals("Andrzej Sapkowski", author.getName());
    }

    @Test
    @DatabaseSetup("/fullData.xml")
    @ExpectedDatabase(value = "/addBookData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addBookCheck() {
        assertEquals(1, publishingManager.getAllBooks().size());

        Book b = new Book();
        b.setIsbn("978-8380082205");
        b.setTitle("Harry Potter i Zakon Feniksa");
        b.setApproved(false);

        publishingManager.addNewBook(b);
        assertEquals(2, publishingManager.getAllBooks().size());
    }

    @Test
    @DatabaseSetup("/fullData.xml")
    @ExpectedDatabase(value = "/deleteBookData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void disapproveBookCheck() {
        assertEquals(1, publishingManager.getAllBooks().size());
        Author author = publishingManager.findAuthorByIdNumber("1234");

        Long bookId = publishingManager.getAuthorsBooks(author).get(0).getId();
        Book bookIdNumber = publishingManager.findBookById(bookId);

        publishingManager.deleteBook(author, bookIdNumber);
        assertEquals(0, publishingManager.getAuthorsBooks(author).size());
        assertEquals(2, publishingManager.getAllBooks().size());
    }

    @Test
    @DatabaseSetup("/fullData.xml")
    @ExpectedDatabase(value = "/approveBookData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void approveBookCheck() {

        Long bookId = publishingManager.getAllBooks().get(0).getId();
        Book b = publishingManager.findBookById(bookId);
        Author author = publishingManager.findAuthorByIdNumber("5678");

        assertEquals(false, b.getApproved());
        publishingManager.approveBook(author.getId(), bookId);
        assertEquals(true, b.getApproved());
    }

}