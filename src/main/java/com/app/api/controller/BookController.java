package com.app.api.controller;

import com.app.api.entity.Book;
import com.app.api.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    Service service;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public String addBook(@PathVariable long id, @RequestBody Book book)  {
        if (service.addBook(book, id)) {
            return "SUCCESS";
        } else {
            return "SOMETHING WENT WRONG";
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT,
            consumes = "application/json", produces = "application/json")
    public String editBook(@PathVariable("userId") long userId, @RequestBody Book book) {
        if (service.editBook(book, userId)) {
            return "SUCCESS";
        } else {
            return "SOMETHING WENT WRONG";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            consumes = "application/json", produces = "application/json")
    public String deleteBook(@PathVariable long id) {
        if (service.deleteBook(id)) {
            return "SUCCESS";
        } else {
            return "SOMETHING WENT WRONG";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Book getBookById(@PathVariable long id){
        return service.getBookById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String getAllBooks(){
        return service.getAllBooks();
    }
}
