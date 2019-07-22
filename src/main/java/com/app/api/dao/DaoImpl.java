package com.app.api.dao;

import com.app.api.entity.Book;
import com.app.api.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoImpl implements Dao {

    //USER
    @Override
    @Transactional
    public boolean addUser(User user) {
        SessionFactory factory;
        Session session;
        boolean r;
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nSAVING USER.....");
            session.save(user);
            session.getTransaction().commit();
            System.out.println(".....DONE");
            r = true;
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
            r = false;
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return r;
    }

    @Override
    @Transactional
    public boolean editUser(User user) {
        SessionFactory factory;
        Session session;
        boolean r;
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nEDITING USER.....");
            session.update(user);
            session.getTransaction().commit();
            System.out.println(".....DONE");
            r = true;
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
            r = false;
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return r;
    }

    @Override
    @Transactional
    public boolean deleteUser(User user) {
        SessionFactory factory;
        Session session;
        boolean r;
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nDELETING USER.....");
            session.delete(user);
            session.getTransaction().commit();
            System.out.println(".....DONE");
            r = true;
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
            r = false;
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return r;
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        SessionFactory factory;
        Session session;
        User user;
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nFETCHING USER.....");
            user = session.get(User.class, id);
            session.getTransaction().commit();
            System.out.println(".....DONE");
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
            user = null;
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return user;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        SessionFactory factory;
        Session session = null;
        List<User> allUsers = new ArrayList<>();
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nFETCHING SET OF ALL USERS.....");
            Query<User> query = session.createQuery("from User order by username", User.class);
            allUsers = query.getResultList();
            session.getTransaction().commit();
            System.out.println(".....DONE");
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return allUsers;
    }

    //BOOK
    @Override
    @Transactional
    public boolean addBook(Book book) {
        SessionFactory factory;
        Session session;
        boolean r;
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nSAVING BOOK.....");
            session.save(book);
            session.getTransaction().commit();
            System.out.println(".....DONE");
            r = true;
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
            r = false;
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return r;
    }

    @Override
    @Transactional
    public boolean editBook(Book book) {
        SessionFactory factory;
        Session session;
        boolean r;
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nEDITING BOOK.....");
            session.update(book);
            session.getTransaction().commit();
            System.out.println(".....DONE");
            r = true;
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
            r = false;
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return r;
    }

    @Override
    public boolean deleteBook(Book book) {
        SessionFactory factory;
        Session session;
        boolean r;
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nDELETING BOOK.....");
            session.delete(book);
            session.getTransaction().commit();
            System.out.println(".....DONE");
            r = true;
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
            r = false;
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return r;
    }

    @Override
    public Book getBookById(long id) {
        SessionFactory factory;
        Session session;
        Book book;
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nFETCHING BOOK.....");
            book = session.get(Book.class, id);
            session.getTransaction().commit();
            System.out.println("...DONE");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
            book = null;
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        SessionFactory factory;
        Session session;
        List<Book> allBooks = new ArrayList<>();
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nFETCHING SET OF ALL BOOKS.....");
            Query<Book> query = session.createQuery("from Book order by user_id", Book.class);
            allBooks = query.getResultList();
            session.getTransaction().commit();
            System.out.println(".....DONE");
        } catch (Exception e) {
            e.printStackTrace();
            factory.getCurrentSession().getTransaction().rollback();
        } finally {
            factory.getCurrentSession().close();
            factory.close();
        }
        return allBooks;
    }
}
