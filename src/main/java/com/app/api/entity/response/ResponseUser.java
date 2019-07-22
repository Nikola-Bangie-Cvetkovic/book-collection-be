package com.app.api.entity.response;

import com.app.api.entity.Book;

import java.util.List;

public class ResponseUser extends ResponseEntity {
    private String username;
    private List<Book> collection;

    public ResponseUser(long id, List<String> msg, String username, List<Book> collection) {
        super(id, msg);
        this.username = username;
        this.collection = collection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getCollection() {
        return collection;
    }

    public void setCollection(List<Book> collection) {
        this.collection = collection;
    }
}
