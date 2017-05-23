package pl.edu.pjwstk.zad7.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.zad7.domain.Book;
import pl.edu.pjwstk.zad7.service.BookManagerImpl;

import javax.enterprise.inject.Model;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class BookWebApi {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    BookManagerImpl bookManager;


    @RequestMapping("/")
    public String index() {
        return "It works";
    }


    //GET specific book
    @RequestMapping(
            value = "/book/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Book getBook(@PathVariable long id) {
        return bookManager.getBook(id);
    }


    //GET all books
    @RequestMapping(
            value = "/books",
            method = RequestMethod.GET,

            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<Book> getAllBooks() {
        return bookManager.getAllBooks();
    }


    //POST one book
    @RequestMapping(
            value = "/book",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    @ResponseBody
    public int add(@ModelAttribute Book book) {
        Book model = new Book();
        model.setAuthor(book.getAuthor());
        model.setTitle(book.getTitle());
        model.setIsbn(book.getIsbn());
        return bookManager.addBook(model);
    }

    //PUT(edit) specific book
    @RequestMapping(
            value = "/book/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public void updateBook(@PathVariable("id") long id, @ModelAttribute Book book) {
        Book model = new Book();
        model.setAuthor(book.getAuthor());
        model.setTitle(book.getTitle());
        model.setIsbn(book.getIsbn());
        model.setId(id);
        bookManager.updateBook(model);
    }


    //DELETE specific book
    @RequestMapping(
            value = "/book/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public void deleteBook(@PathVariable long id) {
        bookManager.deleteBook(id);
    }
}