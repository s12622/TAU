package pl.edu.pjwstk.zad11.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pjwstk.zad11.domain.Author;
import pl.edu.pjwstk.zad11.domain.Book;

@Component
@Transactional
public class PublishingManagerHibernateImpl implements PublishingManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addAuthor(Author author) {
        author.setId(null);
        sessionFactory.getCurrentSession().persist(author);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Author> getAllAuthors() {
        return sessionFactory.getCurrentSession().getNamedQuery("author.all").list();
    }

    @Override
    public void deleteAuthor(Author author) {
        author = (Author) sessionFactory.getCurrentSession().get(Author.class, author.getId());
        for(Book book: author.getBooks()) {
            book.setApproved(false);
            sessionFactory.getCurrentSession().update(book);
        }
        sessionFactory.getCurrentSession().delete(author);

    }

    @Override
    public Author findAuthorByIdNumber(String idNumber) {
        return (Author) sessionFactory.getCurrentSession().getNamedQuery("author.byIdNumber").setString("idNumber", idNumber).uniqueResult();
    }

    @Override
    public Long addNewBook(Book book) {
        book.setId(null);
        return (Long) sessionFactory.getCurrentSession().save(book);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getAllBooks() {
        return sessionFactory.getCurrentSession().getNamedQuery("book.notApproved").list();
    }

    @Override
    public void deleteBook(Author author, Book book) {
        author = (Author) sessionFactory.getCurrentSession().get(Author.class, author.getId());
        book = (Book) sessionFactory.getCurrentSession().get(Book.class, book.getId());

        Book toDelete = null;

        for(Book aBook : author.getBooks())
            if(aBook.getId().compareTo(book.getId())==0) {
                toDelete = aBook;
                break;
            }

            if(toDelete != null)
                author.getBooks().remove(toDelete);

        book.setApproved(false);

    }

    @Override
    public Book findBookById(Long id) {
        return (Book) sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    public List<Book> getAuthorsBooks(Author author) {
        author = (Author) sessionFactory.getCurrentSession().get(Author.class, author.getId());
        List<Book> books = new ArrayList<Book>(author.getBooks());
        return books;
    }

    @Override
    public void approveBook(Long authorId, Long bookId) {
        Author author = (Author) sessionFactory.getCurrentSession().get(Author.class, authorId);
        Book book = (Book) sessionFactory.getCurrentSession().get(Book.class, bookId);
        book.setApproved(true);
        if(author.getBooks() == null) {
            author.setBooks(new LinkedList<Book>());
        }
        author.getBooks().add(book);
    }

    @Override
    public void updateAuthor(Author author) {
        sessionFactory.getCurrentSession().update(author);
    }
}
