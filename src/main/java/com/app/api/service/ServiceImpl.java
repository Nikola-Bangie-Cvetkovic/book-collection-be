package com.app.api.service;

import com.app.api.dao.Dao;
import com.app.api.entity.Book;
import com.app.api.entity.User;
import com.app.api.tools.Tools;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    Dao dao;

    //USER
    @Override
    @Transactional
    public boolean addUser(User user) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        return dao.addUser(user);
    }

    @Override
    @Transactional
    public boolean editUser(User user) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        return dao.editUser(user);
    }

    @Override
    @Transactional
    public boolean deleteUser(long id) {
        return dao.deleteUser(dao.getUserById(id));
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        User user = dao.getUserById(id);
        if (user == null) {
            System.out.println("USER IS NULL");
            User nullUser = new User();
            nullUser.setId((long) -1);
            nullUser.setUsername("NULL");
            nullUser.setPassword("NULL");
            nullUser.setBooks(new ArrayList<>());
            return nullUser;
        } else {
            return user;
        }
    }

    @Override
    @Transactional
    public String getAllUsers() {
        List users = dao.getAllUsers();
        return Tools.toJson(users,users.size());
    }

    //BOOK
    @Override
    @Transactional
    public boolean addBook(Book book, long id) {
        User user = dao.getUserById(id);
        book.setUser(user);
        return dao.addBook(book);
    }

    @Override
    @Transactional
    public boolean editBook(Book book, long userId) {
        book.setUser(dao.getUserById(userId));
        return dao.editBook(book);
    }

    @Override
    @Transactional
    public boolean deleteBook(long id) {
        return dao.deleteBook(dao.getBookById(id));
    }

    @Override
    @Transactional
    public Book getBookById(long id) {
        return dao.getBookById(id);
    }

    @Override
    public String getBooksForUser(long id) {
        User user = dao.getUserById(id);
        return Tools.toJson(user.getBooks(),user.getBooks().size());
    }

    @Override
    public String getAllBooks() {
        List<Book> books = dao.getAllBooks();
        return Tools.toJson(books, books.size());
    }
}