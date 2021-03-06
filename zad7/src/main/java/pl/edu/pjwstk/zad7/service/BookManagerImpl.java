package pl.edu.pjwstk.zad7.service;

import org.springframework.stereotype.Component;
import pl.edu.pjwstk.zad7.domain.Book;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

@Component
public class BookManagerImpl implements BookManager {
    private Connection connection;

    private String url = "jdbc:hsqldb:hsql://localhost/xdb";

    private String createTableBook =
            "CREATE TABLE Book(id bigint GENERATED BY DEFAULT AS IDENTITY, " +
                    "author varchar(40), title varchar(40), isbn varchar(20))";

    private PreparedStatement addBookStmt;
    private PreparedStatement deleteBookStmt;
    private PreparedStatement getAllBooksStmt;
    private PreparedStatement getBookStmt;
    private PreparedStatement updateBookStmt;


    private Statement statement;

    public BookManagerImpl() throws SQLException {
        connection = DriverManager.getConnection(url);
        statement = connection.createStatement();

        ResultSet rs = connection.getMetaData().getTables(null, null, null,
                null);
        boolean tableExists = false;
        while (rs.next()) {
            if ("Book".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                tableExists = true;
                break;
            }
        }

        if (!tableExists) statement.executeUpdate(createTableBook);

        addBookStmt = connection
                .prepareStatement("INSERT INTO Book (author, title, isbn) VALUES (?, ?, ?)");
        deleteBookStmt = connection
                .prepareStatement("DELETE FROM Book WHERE id = ?");
        getAllBooksStmt = connection
                .prepareStatement("SELECT id, author, title, isbn FROM Book");
        getBookStmt = connection
                .prepareStatement("SELECT * FROM Book WHERE id = ?");
        updateBookStmt = connection
                .prepareStatement("UPDATE BOOK SET author=?, title=?, isbn=? WHERE id=?");

    }

    public Connection getConnection() {
        return connection;
    }

    public void deleteBook(long id) {
        try {
            deleteBookStmt.setLong(1, id);
            deleteBookStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateBook(Book book){
        try {
            updateBookStmt.setString(1, book.getAuthor());
            updateBookStmt.setString(2, book.getTitle());
            updateBookStmt.setString(3, book.getIsbn());
            updateBookStmt.setLong(4, book.getId());
            updateBookStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearBooks() throws SQLException {
        connection.prepareStatement("delete from Book").executeUpdate();
        connection.prepareStatement("ALTER TABLE Book ALTER COLUMN ID RESTART WITH 1").executeUpdate();
    }


    public int addBook(Book book) {
        int count = 0;
        try {
            addBookStmt.setString(1, book.getAuthor());
            addBookStmt.setString(2, book.getTitle());
            addBookStmt.setString(3, book.getIsbn());

            count = addBookStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    public Book getBook (long id) {
        Book book = new Book();
        try {


            getBookStmt.setLong(1, id);
            ResultSet rs = getBookStmt.executeQuery();

            if (rs.next()) {
                book.setId(rs.getLong("id"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                book.setIsbn(rs.getString("isbn"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();

        try {
            ResultSet rs = getAllBooksStmt.executeQuery();

            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getInt("id"));
                b.setAuthor(rs.getString("author"));
                b.setTitle(rs.getString("title"));
                b.setIsbn(rs.getString("isbn"));
                books.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


}