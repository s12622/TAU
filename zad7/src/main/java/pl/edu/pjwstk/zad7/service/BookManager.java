package pl.edu.pjwstk.zad7.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pjwstk.zad7.domain.Book;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface BookManager {
    public Connection getConnection();
    public void deleteBook(long id) throws SQLException;
    public void clearBooks() throws SQLException;
    public void updateBook(Book book) throws SQLException;
    public int addBook(Book book);
    public Book getBook(long id);
    public List<Book> getAllBooks();

}
