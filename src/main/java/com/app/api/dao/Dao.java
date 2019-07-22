package com.app.api.dao;

import com.app.api.entity.Book;
import com.app.api.entity.User;
import java.util.List;

public interface Dao {
    //USER
    boolean addUser(User user);
    boolean editUser(User user);
    boolean deleteUser(User user);
    User getUserById(long id);
    List<User> getAllUsers();
    //BOOK
    boolean addBook(Book book);
    boolean editBook(Book book);
    boolean deleteBook(Book book);
    Book getBookById(long id);
    List<Book> getAllBooks();
}
