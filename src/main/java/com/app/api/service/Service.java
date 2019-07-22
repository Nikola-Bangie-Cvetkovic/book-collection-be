package com.app.api.service;

import com.app.api.entity.Book;
import com.app.api.entity.User;
import java.util.List;

public interface Service {
    //USER//
    boolean addUser(User user);
    boolean editUser(User user);
    boolean deleteUser(long id);
    User getUserById(long id);
    String getAllUsers();
    //BOOK//
    boolean addBook(Book book, long id);
    boolean editBook(Book book, long userId);
    boolean deleteBook(long id);
    Book getBookById(long id);
    String getBooksForUser(long id);
    String getAllBooks();
}